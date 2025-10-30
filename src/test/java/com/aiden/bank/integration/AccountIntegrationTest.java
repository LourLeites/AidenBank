package com.aiden.bank.integration;

import com.aiden.bank.model.Account;
import com.aiden.bank.model.Customer;
import com.aiden.bank.repository.IAccountRepository;
import com.aiden.bank.repository.ICustomerRepository;
import com.aiden.bank.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class AccountIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private ICustomerRepository customerRepository;

    private Account account;

    @BeforeEach
    void setUp() {
        Customer customer = Customer.builder()
                .name("Lourdes")
                .email("lour@example.com")
                .documentNumber("12345678")
                .build();
        customerRepository.save(customer);

        account = Account.builder()
                .accountNumber("234")
                .balance(560000.00)
                .type("SAVINGS")
                .customer(customer)
                .build();
        account = accountRepository.save(account);
    }

    @Test
    void shouldCreateAccount() throws Exception {
        Customer newCustomer = Customer.builder()
                .name("Flor")
                .email("flor@example.com")
                .documentNumber("87654321")
                .build();
        customerRepository.save(newCustomer);

        Account newAccount = Account.builder()
                .accountNumber("999")
                .balance(10000.0)
                .type("CHECKING")
                .customer(newCustomer)
                .build();

        String json = objectMapper.writeValueAsString(newAccount);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountNumber").value("999"));
    }

    @Test
    void shouldGetAccountById() throws Exception {
        mockMvc.perform(get("/api/v1/accounts/{id}", account.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountNumber").value("234"));
    }

    @Test
    void shouldUpdateAccount() throws Exception {
        account.setAccountNumber("743");
        String json = objectMapper.writeValueAsString(account);

        mockMvc.perform(put("/api/v1/accounts/{id}", account.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountNumber").value("743"));
    }

    @Test
    void shouldDeleteAccount() throws Exception {
        mockMvc.perform(delete("/api/v1/accounts/{id}", account.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/v1/accounts/{id}", account.getId()))
                .andExpect(status().isNotFound());
    }
}

