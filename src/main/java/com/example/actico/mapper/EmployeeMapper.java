package com.example.actico.mapper;

import com.example.actico.dto.Employee;
import com.example.actico.model.EmployeeModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "departmentName", source = "department.name")
    List<Employee> toEmployees(List<EmployeeModel> employees);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "departmentName", source = "department.name")
    Employee toEmployee(EmployeeModel employee);
}
