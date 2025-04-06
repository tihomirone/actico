package com.example.actico.controller;

import com.example.actico.dto.User;
import com.example.actico.exception.BusinessException;
import com.example.actico.mapper.UserMapper;
import com.example.actico.model.CountryCode;
import com.example.actico.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.actico.exception.ErrorCode.DATA_MISSING_OR_EMPTY;
import static com.example.actico.exception.ErrorCode.NOT_VALID_DATA;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {

        return UserMapper.INSTANCE.toUsers(userService.findAll());
    }

    @GetMapping("/{countryCode}")
    public List<User> getUsersFromCountry(@PathVariable String countryCode) {
        if (!StringUtils.hasText(countryCode)) {
            log.warn("No country code specified!");
            throw new BusinessException("No country code specified!", DATA_MISSING_OR_EMPTY);
        }


        CountryCode code;
        try {
            code = CountryCode.valueOf(countryCode);
        } catch (IllegalArgumentException iae) {
            throw new BusinessException("The country code is not valid!", NOT_VALID_DATA);
        }

        return UserMapper.INSTANCE.toUsers(userService.findByCountryCode(code));
    }

    @GetMapping("/id/{id}")
    public User getUserFromCountry(@PathVariable Long id) {
        if (id == null || id < 1) {
            log.warn("User ID not specified or not positive number {}!!", id);
            throw new BusinessException("User ID not specified or not positive number!", DATA_MISSING_OR_EMPTY);
        }

        if (userService.getById(id).isPresent()) {
            return UserMapper.INSTANCE.toUser(userService.getById(id).get());
        } else {
            log.info("No user found for ID {}", id);
            return null;
        }
    }
}
