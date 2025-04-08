package com.example.actico.persistence;

import com.example.actico.model.CustomerModel;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<CustomerModel, Long> {
}
