package com.example.actico.mapper;

import com.example.actico.dto.Customer;
import com.example.actico.model.CustomerModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = BankAccountMapper.class)
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerModel toCustomerModel(Customer customer);

    Customer toCustomer(CustomerModel customerModel);
}
