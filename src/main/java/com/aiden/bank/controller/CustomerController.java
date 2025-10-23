package com.aiden.bank.controller;

import com.aiden.bank.model.Customer;
import com.aiden.bank.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody @Valid Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerService.create(customer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable(name = "id") Long id) {
        Customer customer = customerService.getById(id);
        return ResponseEntity.ok(customer);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid Customer customer) {
        Customer updatedCustomer = customerService.update(id, customer);
        return ResponseEntity.ok(updatedCustomer); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable(name = "id") Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build(); }
}
