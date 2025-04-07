package com.example.actico.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "EMPLOYEE")
@Data
public class EmployeeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_name")
    private DepartmentModel departmentModel;

    @Column(name = "job_domain")
    @Enumerated(EnumType.STRING)
    private JobDomain jobDomain;

    @Column(name = "level")
    private Long level;
}
