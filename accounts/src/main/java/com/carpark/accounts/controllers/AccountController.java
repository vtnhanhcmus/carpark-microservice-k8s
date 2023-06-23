package com.carpark.accounts.controllers;

import com.carpark.accounts.config.AccountProperties;
import com.carpark.accounts.dtos.AccountDto;
import com.carpark.accounts.models.Accounts;
import com.carpark.accounts.services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("accounts")
@Slf4j
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    AccountProperties accountProperties;

    @GetMapping("{id}/detail")
    public AccountDto getAccountDetails(@PathVariable("id") Long id) {
        return accountService.findById(id);
    }

    @PostMapping("/create")
    public AccountDto createBooking(@RequestBody AccountDto accountRequest){
        return accountService.create(accountRequest);
    }

    @GetMapping("/config")
    public String testProperty(){
        log.info(accountProperties.getMgs());
        return accountProperties.toString();
    }

}
