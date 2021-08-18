package com.swirepay.bankingApp.security.config;


import com.swirepay.bankingApp.dao.AccountRepository;
import com.swirepay.bankingApp.dao.UserRepository;
import com.swirepay.bankingApp.security.CustomUserDetailsAuthenticationProvider;
import com.swirepay.bankingApp.service.CustomUserDetailsService;
import com.swirepay.bankingApp.service.impl.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan("com.swirepay.bankingApp")
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @Bean
    CustomUserDetailsService customUserDetailsService(){
        return new CustomUserDetailsServiceImpl(userRepository,accountRepository);
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/swagger-ui-custom.html").permitAll()
                .antMatchers(HttpMethod.POST,"/api/user").permitAll()
                .antMatchers("/api/account/**").authenticated()
                .and()
                .csrf()
                .disable()
                .httpBasic();
    }



    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    public AuthenticationProvider authProvider() {
        CustomUserDetailsAuthenticationProvider provider
                = new CustomUserDetailsAuthenticationProvider(passwordEncoder(), customUserDetailsService());
        return provider;
    }

}