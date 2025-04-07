package com.example.actico.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@Entity(name = "DEPARTMENT")
@Data
public class DepartmentModel {

    @Id
    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private DepartmentName name;

    @Column(name = "description")
    private String description;

    @Column(name = "emails")
    private String emails;

    @Transient
    private List<String> emailsList;

    @Transient
    public List<String> getEmailsList() {
        if (emailsList == null) {
            if (!StringUtils.hasText(this.emails)) {
                emailsList = List.of();
            } else {
                emailsList = Arrays.asList(emails.split(";"));
            }
        }
        return emailsList;
    }
}
