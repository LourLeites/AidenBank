package com.aiden.bank.unit;

import com.aiden.bank.exception.ApiException;
import com.aiden.bank.model.Account;
import com.aiden.bank.model.Customer;
import com.aiden.bank.repository.IAccountRepository;
import com.aiden.bank.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private IAccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;
    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
        account.setAccountNumber("234");
        account.setBalance(560000.00);
        account.setId(1L);
        account.setType("");
        account.setCustomer(Customer.builder().build());
        account.setOutgoingTransfers(new ArrayList<>());
        account.setIncomingTransfers(new ArrayList<>());
    }

    @Test
    void create_returnCreatedAccount() {
        when(accountRepository.save(account)).thenReturn(account);
        Account result = accountService.create(account);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("234", result.getAccountNumber());
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void getById_returnAccount(){
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        Account result = accountService.getById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("234", result.getAccountNumber());
        verify(accountRepository, times(1)).findById(1L);
    }

    @Test
    void getById_returnError(){
        when(accountRepository.findById(2L)).thenReturn(Optional.empty());
        ApiException exception =  assertThrows(ApiException.class, () -> accountService.getById(2L));

        assertEquals("ACCOUNT_NOT_FOUND", exception.getCode());
        assertEquals("No se encontró la cuenta con id: 2", exception.getMessage());
        verify(accountRepository, times(1)).findById(2L);
    }

    @Test
    void update_returnUpdatedAccount(){
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        account.setAccountNumber("987");
        when(accountRepository.save(any(Account.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        Account result = accountService.update(1L, account);
        assertEquals("987", result.getAccountNumber());
        verify(accountRepository, times(1)).findById(1L);
    }

    @Test
    void delete(){
        accountService.delete(1L);
        verify(accountRepository, times(1)).deleteById(1L);
    }

}
