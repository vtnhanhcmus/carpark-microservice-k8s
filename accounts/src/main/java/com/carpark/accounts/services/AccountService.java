package com.carpark.accounts.services;

import com.carpark.accounts.models.Accounts;
import com.carpark.accounts.repositories.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountsRepository accountsRepository;

    public Accounts findById(Long id){
        Optional<Accounts> account = accountsRepository.findById(id);
        return account.isPresent() ? account.get() : null;
    }

}
