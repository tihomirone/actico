package com.example.actico.service;

import com.example.actico.model.DepartmentName;
import com.example.actico.model.EmployeeModel;
import com.example.actico.persistence.EmployeeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public List<EmployeeModel> findEmployeesByDepartment(DepartmentName departmentName) {
        //return this.employeeRepo.findEmployeeByDepartment_Name(departmentName);
        return this.employeeRepo.findEmployeeByDepartmentName(departmentName);
    }
}
