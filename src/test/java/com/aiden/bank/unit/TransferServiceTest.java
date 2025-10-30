package com.aiden.bank.unit;

import com.aiden.bank.dto.TransferRequest;
import com.aiden.bank.model.Account;
import com.aiden.bank.model.Transfer;
import com.aiden.bank.repository.ITransferRepository;
import com.aiden.bank.service.AccountService;
import com.aiden.bank.service.TransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransferServiceTest {
    @Mock
    private ITransferRepository transferRepository;
    @InjectMocks
    private TransferService transferService;
    @Mock
    private AccountService accountService;

    private Account origin;
    private Account destination;

    @BeforeEach
    void setUp() {
        origin = new Account();
        origin.setId(1L);
        origin.setBalance(1000.0);

        destination = new Account();
        destination.setId(2L);
        destination.setBalance(500.0);
    }

    @Test
    void create_transfer_success() {
        TransferRequest request = new TransferRequest();
        request.setFromAccountId(1L);
        request.setToAccountId(2L);
        request.setAmount(200.0);

        when(accountService.getById(1L)).thenReturn(origin);
        when(accountService.getById(2L)).thenReturn(destination);
        when(transferRepository.save(any(Transfer.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        Transfer result = transferService.create(request);

        assertNotNull(result);
        assertEquals(800.0, origin.getBalance());
        assertEquals(700.0, destination.getBalance());
        assertEquals(200.0, result.getAmount());
        assertEquals("approved", result.getStatus());

        verify(accountService).update(1L, origin);
        verify(accountService).update(2L, destination);
        verify(transferRepository).save(result);
    }
}
