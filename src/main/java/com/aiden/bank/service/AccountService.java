package com.aiden.bank.service;

import com.aiden.bank.exception.ApiException;
import com.aiden.bank.model.Account;
import com.aiden.bank.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private IAccountRepository accountRepository;

    public Account create(Account account){
        return accountRepository.save(account);
    }

    public Account obtain(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ApiException(
                        "ACCOUNT_NOT_FOUND",
                        "No se encontró la cuenta con id: " + id,
                        HttpStatus.NOT_FOUND
                ));
    }

    public Account update(Long id, Account account){
        Account accountToUpdate= obtain(id);
        accountToUpdate.setAccountNumber(account.getAccountNumber());
        return accountRepository.save(accountToUpdate);
    }

    public void delete (Long id){
        accountRepository.deleteById(id);
    }
}
