package com.example.employeeservice.infrastructure.persistence.mapper;

import com.example.employeeservice.domain.model.Employee;
import com.example.employeeservice.domain.model.EmployeeSearchCriteria;
import com.example.employeeservice.domain.model.EmploymentStatus;
import com.example.employeeservice.infrastructure.persistence.dto.EmployeeJpaCriteria;
import com.example.employeeservice.infrastructure.persistence.entity.EmployeeEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeePersistenceMapper {

    public EmployeeJpaCriteria toJpaCriteria(EmployeeSearchCriteria criteria) {
        return new EmployeeJpaCriteria(
                criteria.tenantId(),
                criteria.employeeIdOptional().orElse(null),
                criteria.registrationNumberOptional().orElse(null),
                criteria.documentNumberOptional().orElse(null),
                criteria.statusOptional().orElse(null)
        );
    }

    public Employee toDomain(EmployeeEntity entity) {
        return new Employee(
                String.valueOf(entity.getId()),
                entity.getCompany() != null ? entity.getCompany().getRegistrationNumber() : "UNKNOWN",
                Optional.ofNullable(entity.getRegistrationNumber()).orElse("N/A"),
                entity.getName(),
                Optional.ofNullable(entity.getDocumentNumber()).orElse("N/A"),
                Optional.ofNullable(entity.getStatus()).orElse(EmploymentStatus.ACTIVE),
                entity.getDepartment() != null ? entity.getDepartment().getName() : "General",
                Optional.ofNullable(entity.getContractId()).orElse("N/A")
        );
    }
}