package com.carpark.accounts.services;

import com.carpark.accounts.dtos.AccountDto;
import com.carpark.accounts.models.Accounts;
import com.carpark.accounts.repositories.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountsRepository accountsRepository;

    public AccountDto findById(Long id){
        Optional<Accounts> account = accountsRepository.findById(id);
        if (account.isPresent()){
            return AccountDto.builder().id(account.get().getId()).email(account.get().getEmail()).mobileNumber(account.get().getMobileNumber()).build();
        }
        return null;
    }

}
