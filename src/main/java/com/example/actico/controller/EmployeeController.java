package com.example.actico.controller;

import com.example.actico.dto.Employee;
import com.example.actico.mapper.EmployeeMapper;
import com.example.actico.model.DepartmentName;
import com.example.actico.model.EmployeeModel;
import com.example.actico.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.actico.exception.ErrorCode.NOT_VALID_DATA;

@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/department/{departmentName}")
    public ResponseEntity<List<Employee>> findEmployeesByDepartment(@PathVariable String departmentName) {
        if (!StringUtils.hasText(departmentName)) {
            log.error("No department name specified!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of());
        }
        DepartmentName depName;
        try {
            depName = DepartmentName.valueOf(departmentName);
        } catch (IllegalArgumentException iae) {
            log.error("Not valid country code {}!", NOT_VALID_DATA);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of());
        }
        List<EmployeeModel> employees = employeeService.findEmployeesByDepartment(depName);
        if (employees.isEmpty()) {
            log.info("No employees found for department {}!", departmentName);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(List.of());
        }
        return ResponseEntity.ok(EmployeeMapper.INSTANCE.toEmployees(employees));
    }
}
