package com.example.actico.controller;

import com.example.actico.dto.BankAccount;
import com.example.actico.dto.Customer;
import com.example.actico.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.customerService.createNewCustomer(customer));
    }

    @PutMapping("/bank-account/id/{customerId}")
    public ResponseEntity<Customer> addBankAccountToCustomer(@RequestBody BankAccount bankAccount,
                                                             @PathVariable Long customerId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addBankAccountToCustomer(bankAccount, customerId));
    }
}
