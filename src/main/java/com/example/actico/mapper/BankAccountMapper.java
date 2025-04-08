package com.example.actico.mapper;

import com.example.actico.dto.BankAccount;
import com.example.actico.model.BankAccountModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BankAccountMapper {

    BankAccountMapper INSTANCE = Mappers.getMapper(BankAccountMapper.class);

    @Mapping(target = "customer_id", source = "customer.id")
    BankAccount toBankAccount(BankAccountModel bankAccountModel);

    @Mapping(target = "customer", ignore = true)
    BankAccountModel toBankAccountModel(BankAccount bankAccount);
}
