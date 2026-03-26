package com.example.employeeservice.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "work_location")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkLocationEntity {

    @Id
    private Long id;

    private String name; // Ex: Paço Municipal

    private String street;
    private String number;
    private String city;
    private String state;
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;
}