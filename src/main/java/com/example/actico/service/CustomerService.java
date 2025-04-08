package com.example.actico.service;

import com.example.actico.dto.BankAccount;
import com.example.actico.dto.Customer;
import com.example.actico.mapper.BankAccountMapper;
import com.example.actico.mapper.CustomerMapper;
import com.example.actico.model.BankAccountModel;
import com.example.actico.model.CustomerModel;
import com.example.actico.persistence.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerService {

    private final CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public Customer createNewCustomer(Customer customer) {
        CustomerModel savedCustomer = customerRepo.save(CustomerMapper.INSTANCE.toCustomerModel(customer));
        return CustomerMapper.INSTANCE.toCustomer(savedCustomer);
    }

    public Customer addBankAccountToCustomer(BankAccount bankAccount, Long customerId) {
        CustomerModel customerModel = customerRepo.findById(customerId).get();
        return CustomerMapper.INSTANCE.toCustomer(addBankAccount(customerModel, bankAccount));
    }

    private CustomerModel addBankAccount(CustomerModel customerModel, BankAccount bankAccount) {
        if (customerModel.getBankAccounts() == null ) {
            customerModel.setBankAccounts(new ArrayList<>());
        }
        BankAccountModel bankAccountModel = BankAccountMapper.INSTANCE.toBankAccountModel(bankAccount);
        bankAccountModel.setCustomer(customerModel);
        customerModel.getBankAccounts().add(bankAccountModel);
        return customerRepo.save(customerModel);
    }
}
