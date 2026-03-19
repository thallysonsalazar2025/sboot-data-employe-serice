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

    public Optional<String> employeeIdOptional() {
        return Optional.ofNullable(employeeId);
    }

    public Optional<String> registrationNumberOptional() {
        return Optional.ofNullable(registrationNumber);
    }

    public Optional<String> documentNumberOptional() {
        return Optional.ofNullable(documentNumber);
    }

    public Optional<EmploymentStatus> statusOptional() {
        return Optional.ofNullable(status);
    }
}
