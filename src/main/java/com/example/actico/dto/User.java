package com.example.actico.dto;

import com.example.actico.model.CountryCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private String name;
    private String email;
    private String city;
    private CountryCode countryCode;
}
