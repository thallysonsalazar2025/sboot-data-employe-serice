package com.example.employeeservice.domain.model;

import java.util.Optional;

public record EmployeeSearchCriteria(
        String tenantId,
        String correlationId,
        String employeeId,
        String registrationNumber,
        String documentNumber,
        EmploymentStatus status
) {

    public Optional<String> employeeId() {
        return Optional.ofNullable(employeeId);
    }

    public Optional<String> registrationNumber() {
        return Optional.ofNullable(registrationNumber);
    }

    public Optional<String> documentNumber() {
        return Optional.ofNullable(documentNumber);
    }

    public Optional<EmploymentStatus> status() {
        return Optional.ofNullable(status);
    }
}
