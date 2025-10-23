package com.aiden.bank;

import com.aiden.bank.exception.ApiException;
import com.aiden.bank.model.Customer;
import com.aiden.bank.repository.ICustomerRepository;
import com.aiden.bank.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private ICustomerRepository customerRepository;
    @InjectMocks
    private CustomerService customerService;

    @Test
    void getById_existingCustomer_returnsCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Lour");
        customer.setEmail("lour@example.com");

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer result = customerService.getById(1L);

        assertNotNull(result);
        assertEquals("Lour", result.getName());
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    void getById_nonExistingCustomer_throwsApiException() {
        when(customerRepository.findById(99L)).thenReturn(Optional.empty());

        ApiException exception = assertThrows(ApiException.class, () -> customerService.getById(99L));

        assertEquals("CUSTOMER_NOT_FOUND", exception.getCode());
        assertEquals("Customer not found", exception.getMessage());
        verify(customerRepository, times(1)).findById(99L);
    }
}

