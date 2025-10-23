package com.aiden.bank.service;

import com.aiden.bank.dto.TransferRequest;
import com.aiden.bank.exception.ApiException;
import com.aiden.bank.model.Account;
import com.aiden.bank.model.Transfer;
import com.aiden.bank.repository.ITransferRepository;
import jakarta.transaction.Transactional;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class TransferService {
    @Autowired
    private ITransferRepository transferRepository;
    @Autowired
    private AccountService accountService;

    @Transactional
    public Transfer create(TransferRequest request) {
        Account originAccount = accountService.obtain(request.getFromAccountId());
        Account destinationAccount = accountService.obtain(request.getToAccountId());
        if (originAccount.getBalance() < request.getAmount()) {
            throw new ApiException("INSUFFICIENT_BALANCE", "Saldo insuficiente en la cuenta origen", HttpStatus.BAD_REQUEST);
        }
        originAccount.setBalance(originAccount.getBalance() - request.getAmount());
        destinationAccount.setBalance(destinationAccount.getBalance() + request.getAmount());
        accountService.update(originAccount.getId(), originAccount);
        accountService.update(destinationAccount.getId(), destinationAccount);
        Transfer transfer = new Transfer();
        transfer.setSourceAccount(originAccount);
        transfer.setDestinationAccount(destinationAccount);
        transfer.setAmount(request.getAmount());
        transfer.setDate(LocalDateTime.now());
        transfer.setStatus("approved");

        return transferRepository.save(transfer);
    }
}
