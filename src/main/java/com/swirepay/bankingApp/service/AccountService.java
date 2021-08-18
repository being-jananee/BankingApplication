package com.swirepay.bankingApp.service;

import com.swirepay.bankingApp.model.Account;
import com.swirepay.bankingApp.model.AccountPayload;

public interface AccountService {
     Account addCharge(AccountPayload account);

    Float getBalance(Integer accountId);

    Account refund(AccountPayload account) throws Exception;

    void transfer(Float charge,Integer contactId) throws Exception;
}
