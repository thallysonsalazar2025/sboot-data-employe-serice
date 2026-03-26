package com.example.employeeservice.infrastructure.mapper;

import com.example.employeeservice.application.dto.EmployeeSearchRequest;
import com.example.employeeservice.domain.model.EmployeeSearchCriteria;
import org.springframework.stereotype.Component;

@Component
public class EmployeeSearchMapper {

    public EmployeeSearchCriteria toCriteria(EmployeeSearchRequest request) {
        return new EmployeeSearchCriteria(
                request.tenantId(),
                request.correlationId(),
                request.employeeId(),
                request.registrationNumber(),
                request.documentNumber(),
                request.status()
        );
    }
}