package com.example.employeeservice.application.mapper;

import com.example.employeeservice.application.dto.EmployeeDataResponse;
import com.example.employeeservice.domain.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeResponseMapper {

    public EmployeeDataResponse toResponse(Employee employee) {
        return new EmployeeDataResponse(
                employee.id(),
                employee.registrationNumber(),
                employee.fullName(),
                employee.documentNumber(),
                employee.status(),
                employee.department(),
                employee.payrollCode()
        );
    }
}
