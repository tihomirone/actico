package com.example.actico.persistence;

import com.example.actico.model.UserModel;
import org.springframework.data.repository.CrudRepository;


public interface UsersRepo extends CrudRepository<UserModel, Long> {
}
