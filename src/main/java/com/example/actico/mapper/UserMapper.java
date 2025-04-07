package com.example.actico.mapper;

import com.example.actico.dto.User;
import com.example.actico.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(UserModel userModel);

    List<User> toUsers(List<UserModel> userModels);
}
