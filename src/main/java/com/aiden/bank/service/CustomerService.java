package com.aiden.bank.service;

import com.aiden.bank.exception.ApiException;
import com.aiden.bank.model.Customer;
import com.aiden.bank.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    public ICustomerRepository customerRepository;

    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ApiException(
                        "CUSTOMER_NOT_FOUND",
                        "No se encontró el cliente con id: " + id,
                        HttpStatus.NOT_FOUND
                ));
    }

    public Customer update(Long id, Customer customer) {
        Customer existingCustomer = getById(id);
        customer.setId(existingCustomer.getId());
        return customerRepository.save(customer);
    }

    public void delete(Long id) {
        Customer customer = getById(id);
        customerRepository.delete(customer);
    }
}

