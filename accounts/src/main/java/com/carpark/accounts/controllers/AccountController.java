package com.carpark.accounts.controllers;

import com.carpark.accounts.models.Accounts;
import com.carpark.accounts.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("{id}")
    public Accounts getAccountDetails(@PathVariable("id") Long id) {
        return accountService.findById(id);
    }

}
