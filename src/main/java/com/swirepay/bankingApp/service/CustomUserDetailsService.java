package com.swirepay.bankingApp.service;

import com.swirepay.bankingApp.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomUserDetailsService {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    void addContact(int contactAccountId) throws Exception;

    User getLoginUser();
}
