package com.example.employeeservice.infrastructure.persistence.dto;

import com.example.employeeservice.domain.model.EmploymentStatus;

public record EmployeeJpaCriteria(
        String tenantId,
        String employeeId,
        String registrationNumber,
        String documentNumber,
        EmploymentStatus status
) {}