package com.example.actico.dto;

import lombok.Data;

import java.util.List;

@Data
public class Customer {

    private String name;

    private List<BankAccount> bankAccounts;
}
