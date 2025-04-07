package com.example.actico.controller;

import com.example.actico.dto.User;
import com.example.actico.mapper.UserMapper;
import com.example.actico.model.CountryCode;
import com.example.actico.model.UserModel;
import com.example.actico.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.actico.exception.ErrorCode.NOT_VALID_DATA;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(UserMapper.INSTANCE.toUsers(userService.findAll()));
    }

    @GetMapping("/country-code/{countryCode}")
    public ResponseEntity<List<User>> getUsersFromCountry(@PathVariable String countryCode) {
        if (!StringUtils.hasText(countryCode)) {
            log.error("No country code specified!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of());
        }
        CountryCode code;
        try {
            code = CountryCode.valueOf(countryCode);
        } catch (IllegalArgumentException iae) {
            log.error("Not valid country code {}!", NOT_VALID_DATA);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of());
        }
        List<UserModel> usersFound = userService.findByCountryCode(code);
        if (usersFound.isEmpty()) {
            log.info("No users found for country code {}!", countryCode);
        }
        return ResponseEntity.ok(UserMapper.INSTANCE.toUsers(usersFound));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserFromCountry(@PathVariable Long id) {
        if (id == null || id < 1) {
            log.error("User ID not specified or not positive number {}!", id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        if (userService.getById(id).isPresent()) {
            return ResponseEntity.ok(UserMapper.INSTANCE.toUser(userService.getById(id).get()));
        } else {
            log.error("No user found for ID {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
