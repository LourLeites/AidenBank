package com.aiden.bank.unit;

import com.aiden.bank.exception.ApiException;
import com.aiden.bank.model.Customer;
import com.aiden.bank.repository.ICustomerRepository;
import com.aiden.bank.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private ICustomerRepository customerRepository;
    @InjectMocks
    private CustomerService customerService;
    private Customer customer;
    @BeforeEach
    void setUp(){
    customer = new Customer();
    customer.setId(1L);
    customer.setName("Lour");
    customer.setEmail("lour@example.com");
}
    @Test
    void getById_existingCustomer_returnsCustomer() {
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
        assertEquals("No se encontró el cliente con id: 99", exception.getMessage());
        verify(customerRepository, times(1)).findById(99L);
    }

    @Test
    void update_returnsCustomerUpdate() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        customer.setName("Lour actualizado");
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        Customer result = customerService.update(1L, customer);
        assertEquals("Lour actualizado", result.getName());
    }

    @Test
    void delete(){
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        customerService.delete(1L);
        verify(customerRepository, times(1)).findById(1L);
        verify(customerRepository, times(1)).delete(customer);
    }
}

