package com.example.actico.persistence;

import com.example.actico.model.CountryCode;
import com.example.actico.model.UserModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UsersRepo extends CrudRepository<UserModel, Long> {

    List<UserModel> findByCountryCode(CountryCode countryCode);
}
