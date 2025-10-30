package com.aiden.bank.integration;

import com.aiden.bank.model.Account;
import com.aiden.bank.model.Customer;
import com.aiden.bank.model.Transfer;
import com.aiden.bank.dto.TransferRequest;
import com.aiden.bank.repository.IAccountRepository;
import com.aiden.bank.repository.ICustomerRepository;
import com.aiden.bank.repository.ITransferRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TransferIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private ITransferRepository transferRepository;

    private Account originAccount;
    private Account destinationAccount;

    @BeforeEach
    void setUp() {
        transferRepository.deleteAll();
        accountRepository.deleteAll();
        customerRepository.deleteAll();

        Customer customer = new Customer();
        customer.setName("Lour");
        customer.setEmail("lour@example.com");
        customer.setDocumentNumber("12345678");
        customerRepository.save(customer);

        originAccount = new Account();
        originAccount.setAccountNumber("111111");
        originAccount.setBalance(1000.0);
        originAccount.setType("SAVINGS");
        originAccount.setCustomer(customer);
        originAccount.setIncomingTransfers(new ArrayList<>());
        originAccount.setOutgoingTransfers(new ArrayList<>());
        accountRepository.save(originAccount);

        destinationAccount = new Account();
        destinationAccount.setAccountNumber("222222");
        destinationAccount.setBalance(500.0);
        destinationAccount.setType("SAVINGS");
        destinationAccount.setCustomer(customer);
        destinationAccount.setIncomingTransfers(new ArrayList<>());
        destinationAccount.setOutgoingTransfers(new ArrayList<>());
        accountRepository.save(destinationAccount);
    }

    @Test
    void shouldCreateTransfer() throws Exception {
        TransferRequest request = new TransferRequest();
        request.setFromAccountId(originAccount.getId());
        request.setToAccountId(destinationAccount.getId());
        request.setAmount(200.00);

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/v1/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.amount").value(200.00));
    }
}
