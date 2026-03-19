package com.example.employeeservice.application.validator;

import com.example.employeeservice.domain.model.EmployeeSearchCriteria;
import com.example.employeeservice.infrastructure.exception.BusinessValidationException;
import org.springframework.stereotype.Component;

@Component
public class EmployeeSearchValidator {

    public void validate(EmployeeSearchCriteria criteria) {
        boolean hasFilter = criteria.employeeId().isPresent()
                || criteria.registrationNumber().isPresent()
                || criteria.documentNumber().isPresent()
                || criteria.status().isPresent();

        if (!hasFilter) {
            throw new BusinessValidationException("At least one search filter must be informed");
        }

        criteria.documentNumber()
                .filter(document -> !document.matches("\\d{11}|\\d{14}"))
                .ifPresent(document -> {
                    throw new BusinessValidationException("documentNumber must contain 11 or 14 digits");
                });
    }
}
