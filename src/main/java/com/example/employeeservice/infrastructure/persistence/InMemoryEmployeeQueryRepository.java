package com.example.employeeservice.infrastructure.persistence;

import com.example.employeeservice.domain.model.Employee;
import com.example.employeeservice.domain.model.EmployeeSearchCriteria;
import com.example.employeeservice.domain.model.EmploymentStatus;
import com.example.employeeservice.domain.port.out.EmployeeQueryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryEmployeeQueryRepository implements EmployeeQueryRepository {

    private static final List<Employee> EMPLOYEES = List.of(
            new Employee("1", "tenant-a", "REG-100", "Ana Souza", "12345678901", EmploymentStatus.ACTIVE, "Finance", "PAY-001"),
            new Employee("2", "tenant-a", "REG-101", "Carlos Lima", "10987654321", EmploymentStatus.LEAVE, "HR", "PAY-002"),
            new Employee("3", "tenant-b", "REG-200", "Marina Alves", "11122233344", EmploymentStatus.ACTIVE, "Technology", "PAY-010")
    );

    @Override
    public List<Employee> findByCriteria(EmployeeSearchCriteria criteria) {
        return EMPLOYEES.stream()
                .filter(employee -> employee.tenantId().equals(criteria.tenantId()))
                .filter(employee -> criteria.employeeIdOptional().map(id -> employee.id().equals(id)).orElse(true))
                .filter(employee -> criteria.registrationNumberOptional().map(value -> employee.registrationNumber().equals(value)).orElse(true))
                .filter(employee -> criteria.documentNumberOptional().map(value -> employee.documentNumber().equals(value)).orElse(true))
                .filter(employee -> criteria.statusOptional().map(value -> employee.status() == value).orElse(true))
                .toList();
    }
}
