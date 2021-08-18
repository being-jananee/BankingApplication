package com.swirepay.bankingApp.dao;

import com.swirepay.bankingApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("from User where user_name = ?1")
    public User getAccountByUserName(String userName);
}
