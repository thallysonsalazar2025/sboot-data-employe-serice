package com.example.employeeservice.application.dto;

import com.example.employeeservice.domain.model.EmploymentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmployeeSearchRequest(
        @NotBlank(message = "tenantId is required")
        @Size(max = 50, message = "tenantId must have at most 50 characters")
        String tenantId,
        @NotBlank(message = "correlationId is required")
        @Size(max = 100, message = "correlationId must have at most 100 characters")
        String correlationId,
        @Size(max = 50, message = "employeeId must have at most 50 characters")
        String employeeId,
        @Size(max = 30, message = "registrationNumber must have at most 30 characters")
        String registrationNumber,
        @Size(max = 20, message = "documentNumber must have at most 20 characters")
        String documentNumber,
        EmploymentStatus status
) {
}
