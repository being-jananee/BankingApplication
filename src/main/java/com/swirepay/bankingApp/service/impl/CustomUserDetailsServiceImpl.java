package com.swirepay.bankingApp.service.impl;

import com.swirepay.bankingApp.dao.AccountRepository;
import com.swirepay.bankingApp.dao.UserRepository;
import com.swirepay.bankingApp.model.Account;
import com.swirepay.bankingApp.model.User;
import com.swirepay.bankingApp.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service("userDetailsService")
@AllArgsConstructor
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    /**
     * loadUserByUsername method to fetch user based on username
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("Username must be provided");
        }
        User accountHolder = userRepository.getAccountByUserName(username);
        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(username, accountHolder.getPassword(), new ArrayList<>());
        if (user == null) {
            throw new UsernameNotFoundException(
                    String.format("Username not found, username=%s",
                            username));
        }
        return user;
    }

    /**
     * addContact to user
     * @param contactAccountId
     * @throws Exception
     */
    @Override
    public void addContact(int contactAccountId) throws Exception{
        User accountHolder = getLoginUser();
        Optional<Account> contact = accountRepository.findById(contactAccountId);
        if(accountHolder.getAccount().getAccountId()==contactAccountId){
            throw new Exception("Cannot add self as contact");
        }
        if(contact.isPresent()){
            accountHolder.addContact(contactAccountId);
            userRepository.save(accountHolder);
        }
        else{
            throw new Exception("Contact Id not found");
        }

    }

    /**
     * get current logged in user
     * @return
     */
    @Override
    public User getLoginUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User accountHolder = userRepository.getAccountByUserName((( org.springframework.security.core.userdetails.User)auth.getPrincipal()).getUsername());
        return accountHolder;
    }
}