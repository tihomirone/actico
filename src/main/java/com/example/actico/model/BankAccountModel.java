package com.example.actico.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bank_account")
@Data
public class BankAccountModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "iban")
    private String iban;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "customer_id")
    private CustomerModel customer;
    //private Long customer_id;
}
