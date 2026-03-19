package com.example.employeeservice.application.dto;

import com.example.employeeservice.domain.model.EmploymentStatus;

public record EmployeeDataResponse(
        String id,
        String registrationNumber,
        String fullName,
        String documentNumber,
        EmploymentStatus status,
        String department,
        String payrollCode
) {
}
