package com.example.actico.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "USERS")
@Data
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;
}
