package com.swirepay.bankingApp.controller;

import com.swirepay.bankingApp.model.Account;
import com.swirepay.bankingApp.model.AccountPayload;
import com.swirepay.bankingApp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/balance/{accId}")
    public Float getBalance(@PathVariable("accId") Integer accountId){
        return accountService.getBalance(accountId);
    }

    @PostMapping("/charge")
    public Account addToBalance(@RequestBody AccountPayload account){
        return accountService.addCharge(account);
    }

    @PostMapping("/refund")
    public Account removeFromBalance(@RequestBody AccountPayload account) throws Exception {
        return accountService.refund(account);
    }

    @PostMapping("/transfer/{contactId}")
    public void transferToAccount(@RequestBody AccountPayload account,@PathVariable Integer contactId) throws Exception {
        accountService.transfer(account.getCharge(),contactId);
    }


}
