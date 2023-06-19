package com.carpark.accounts.services;

import com.carpark.accounts.dtos.AccountDto;
import com.carpark.accounts.models.Accounts;
import com.carpark.accounts.repositories.AccountsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
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

    public AccountDto create(AccountDto accountDto){
        log.info("process create account");
        Accounts accounts = convertToEntity(accountDto);
        accountsRepository.save(accounts);
        return convertToDto(accounts);
    }

    private AccountDto convertToDto(Accounts accounts){
        return AccountDto.builder().id(accounts.getId()).name(accounts.getName()).email(accounts.getEmail()).mobileNumber(accounts.getMobileNumber()).build();
    }

    private Accounts convertToEntity(AccountDto accountDto){
        return Accounts.builder().id(accountDto.getId()).name(accountDto.getName()).email(accountDto.getEmail()).mobileNumber(accountDto.getMobileNumber()).build();
    }

}
