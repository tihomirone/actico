package com.example.actico.dto;


import com.example.actico.model.DepartmentName;
import com.example.actico.model.JobDomain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {

    private Long userId;
    private DepartmentName departmentName;
    private JobDomain jobDomain;
    private Long level;
}
