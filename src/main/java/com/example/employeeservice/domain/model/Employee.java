package com.example.employeeservice.domain.model;

public record Employee(
        String id,
        String tenantId,
        String registrationNumber,
        String fullName,
        String documentNumber,
        EmploymentStatus status,
        String department,
        String payrollCode
) {
}
