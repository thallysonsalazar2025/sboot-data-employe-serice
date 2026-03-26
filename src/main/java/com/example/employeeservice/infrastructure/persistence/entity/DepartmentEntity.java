package com.example.employeeservice.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "department")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentEntity {

    @Id
    private Long id;

    private String name; // Ex: Secretaria da Fazenda

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;
}