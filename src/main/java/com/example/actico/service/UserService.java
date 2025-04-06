package com.example.actico.service;

import com.example.actico.model.CountryCode;
import com.example.actico.model.UserModel;
import com.example.actico.persistence.UsersRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    private final UsersRepo usersRepo;

    public UserService(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    public Optional<UserModel> getById(Long id) {
        return usersRepo.findById(id);
    }

    public List<UserModel> findByCountryCode(CountryCode countryCode) {
        if (countryCode == null) {
            throw new RuntimeException("Country code should be not empty!");
        }
        return usersRepo.findByCountryCode(countryCode);
    }

    public List<UserModel> findAll() {
        return StreamSupport.stream(usersRepo.findAll().spliterator(), false).toList();
    }
}
