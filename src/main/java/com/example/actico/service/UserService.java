package com.example.actico.service;

import com.example.actico.dto.User;
import com.example.actico.exception.BusinessException;
import com.example.actico.mapper.UserMapper;
import com.example.actico.model.CountryCode;
import com.example.actico.model.UserModel;
import com.example.actico.persistence.UsersRepo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static com.example.actico.exception.ErrorCode.DATA_MISSING_OR_EMPTY;
import static com.example.actico.exception.ErrorCode.NOT_VALID_DATA;

@Service
@Slf4j
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
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

    public boolean validateUser(User user) {
        if (user == null) {
            log.error("User is empty!");
            throw new BusinessException("User is empty!", DATA_MISSING_OR_EMPTY);
        }
        validateTextFieldNotEmpty(user.getName(), "User name is empty!");
        validateTextFieldNotEmpty(user.getEmail(), "User email is empty!");
        validateTextFieldNotEmpty(user.getCity(), "User city is empty!");
        validateTextFieldNotEmpty(user.getCountryCode(), "User country code is empty!");
        if (user.getCountryCode().length() != 2) {
            log.error("User country code should be with exactly 2 letters, but is {}", user.getCountryCode());
            throw new BusinessException("User country code should be with exactly 2 letters", NOT_VALID_DATA);
        }
        return true;
    }

    public User createNewUser(User user) {
        UserModel userModel = UserMapper.INSTANCE.toUserModel(user);
        UserModel savedUserModel = usersRepo.save(userModel);
        return UserMapper.INSTANCE.toUser(savedUserModel);
    }

    private boolean validateTextFieldNotEmpty(String field, String errorMessage) {
        if (!StringUtils.hasText(field)) {
            log.error(errorMessage);
            throw new BusinessException(errorMessage, NOT_VALID_DATA);
        }
        return true;
    }
}
