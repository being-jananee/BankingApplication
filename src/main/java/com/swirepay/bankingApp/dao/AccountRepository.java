package com.swirepay.bankingApp.dao;

import com.swirepay.bankingApp.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AccountRepository extends JpaRepository<Account,Integer> {

    @Query("select balance from Account where accountId = ?1")
    public Float getBalanceByAcctID(Integer acctID);

    @Transactional
    @Query("update Account set balance = balance+?2 where accountId=?1")
    @Modifying(clearAutomatically = true)
    public void addChargeByAcctID(Integer acctID, Float charge);

    @Transactional
    @Query("update Account set balance = balance-?2 where accountId=?1")
    @Modifying(clearAutomatically = true)
    public void refundAmountByAcctID(Integer acctID, Float refund);

}
