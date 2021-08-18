package com.swirepay.bankingApp.controller;

import com.swirepay.bankingApp.dao.UserRepository;
import com.swirepay.bankingApp.model.Account;
import com.swirepay.bankingApp.model.User;
import com.swirepay.bankingApp.model.UserPayload;
import com.swirepay.bankingApp.service.AccountService;
import com.swirepay.bankingApp.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody UserPayload userPayload) throws Exception {
        User user =UserPayload.toUser(userPayload);
        if(Objects.nonNull(userRepository.getAccountByUserName(user.getUserName()))){
            throw new Exception("UserName exists already");
        }
        user.setAccount(new Account());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
       return userRepository.save(user);
    }

    @GetMapping("/addContact/{contactId}")
    public void addContacts(@PathVariable("contactId") int contactAccountId) throws Exception {
        customUserDetailsService.addContact(contactAccountId);
    }

}
