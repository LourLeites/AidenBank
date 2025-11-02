package com.aiden.bank.integration;

import com.aiden.bank.model.Customer;
import com.aiden.bank.repository.ICustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/*
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CustomerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private ObjectMapper objectMapper;

    private Customer baseCustomer;

    @BeforeEach
    void setup() {
        customerRepository.deleteAll();
        baseCustomer = new Customer();
        baseCustomer.setName("Lour");
        baseCustomer.setEmail("lour@example.com");
        baseCustomer.setDocumentNumber("12345678");
        customerRepository.save(baseCustomer);
    }

    @Test
    void shouldCreateCustomer() throws Exception {
        String customerJson = """
            {
                "name": "Lourdes",
                "email": "lourdes@example.com",
                "documentNumber": "12345678"
            }
        """;

        mockMvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Lourdes"))
                .andExpect(jsonPath("$.email").value("lourdes@example.com"));
    }

    @Test
    void shouldGetCustomerById() throws Exception {

        mockMvc.perform(get("/api/v1/customers/{id}", baseCustomer.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Lour"))
                .andExpect(jsonPath("$.email").value("lour@example.com"));
    }

    @Test
    void shouldUpdateCustomer() throws Exception {
        baseCustomer.setName("Flor");

        String json = objectMapper.writeValueAsString(baseCustomer);

        mockMvc.perform(put("/api/v1/customers/{id}", baseCustomer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Flor"));
    }

    @Test
    void shouldDeleteCustomer() throws Exception {

        mockMvc.perform(delete("/api/v1/customers/{id}", baseCustomer.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/v1/customers/{id}", baseCustomer.getId()))
                .andExpect(status().isNotFound());
    }
}
*/