package com.swirepay.bankingApp.model;

import lombok.Data;

@Data
public class UserPayload {

    private String firstName;

    private String lastName;

    private String userName;

    private String password;

    public static User toUser(UserPayload userPayload){
       User user = new User();
       user.setFirstName(userPayload.getFirstName());
       user.setLastName(userPayload.getLastName());
       user.setUserName(userPayload.getUserName());
       user.setPassword(userPayload.getPassword());
       return user;
    }
}
