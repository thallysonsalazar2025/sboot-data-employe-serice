package com.example.employeeservice.infrastructure.persistence.entity;

import com.example.employeeservice.domain.model.EmploymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity {

    @Id
    private Long id;

    private String name;

    private String email;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    private String registrationNumber;

    private String documentNumber;

    @Enumerated(EnumType.STRING)
    private EmploymentStatus status;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;

    @ManyToOne
    @JoinColumn(name = "job_role_id")
    private JobRoleEntity jobRole;

    @ManyToOne
    @JoinColumn(name = "work_location_id")
    private WorkLocationEntity workLocation;

    private String contractId;
}
