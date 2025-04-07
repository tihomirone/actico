package com.example.actico.persistence;

import com.example.actico.model.DepartmentName;
import com.example.actico.model.EmployeeModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepo extends CrudRepository<EmployeeModel, Long> {

    List<EmployeeModel> findEmployeeByDepartment_Name(DepartmentName departmentName);

    @Query("select e from EmployeeModel e INNER JOIN e.department d where d.name = :departmentName")
    List<EmployeeModel> findEmployeeByDepartmentName(DepartmentName departmentName);
}
