package com.example.employeeservice.application.dto;

import java.util.List;

public record EmployeeSearchResponse(
        String tenantId,
        String correlationId,
        int totalRecords,
        List<EmployeeDataResponse> employees
) {
}
