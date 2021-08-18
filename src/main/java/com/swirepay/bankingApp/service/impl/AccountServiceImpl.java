package com.swirepay.bankingApp.service.impl;

import com.swirepay.bankingApp.dao.AccountRepository;
import com.swirepay.bankingApp.model.Account;
import com.swirepay.bankingApp.model.AccountPayload;
import com.swirepay.bankingApp.model.User;
import com.swirepay.bankingApp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsService;

    /**
     * addCharge method to add charge to an account
     * @param account
     * @return the updated account data
     */
    @Override
    public Account addCharge(AccountPayload account) {
        User  user = customUserDetailsService.getLoginUser();
        Integer accountId = user.getAccount().getAccountId();
        Float charge = account.getCharge();
        accountRepository.addChargeByAcctID(accountId,charge);
        return accountRepository.getById(accountId);
    }

    /**
     * getBalance returns current balance of account
     * @param accountId
     * @return the balance amount
     */
    @Override
    public Float getBalance(Integer accountId) {
              return accountRepository.getBalanceByAcctID(accountId);
    }

    /**
     * refund to deduct amount from account
     * @param account
     * @return the updated account data
     * @throws Exception
     */
    @Override
    public Account refund(AccountPayload account) throws Exception {
        User  user = customUserDetailsService.getLoginUser();
        Integer accountId = user.getAccount().getAccountId();
        Float charge = account.getCharge();
        if( user.getAccount().getBalance()>charge) {
            accountRepository.refundAmountByAcctID(user.getAccount().getAccountId(), account.getCharge());
            return accountRepository.getById(accountId);
        }else{
            throw new Exception("Insufficient Balance");
        }
    }

    /**
     * transfer method to make fund transfer to contact
     * @param charge
     * @param contactId
     * @throws Exception
     */
    @Override
    public void transfer(Float charge, Integer contactId) throws Exception {
        User  user = customUserDetailsService.getLoginUser();
        if(!user.getContacts().contains(contactId)) {
            throw new Exception("ContactId not Found,Please add Contact");
        }
            if (user.getAccount().getBalance() > charge) {
                accountRepository.refundAmountByAcctID(user.getUserId(), charge);
                accountRepository.addChargeByAcctID(contactId, charge);
            } else {
                throw new Exception("Insufficient Balance");
            }


    }

}
