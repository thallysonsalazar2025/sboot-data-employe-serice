package com.example.employeeservice.infrastructure.persistence;

import com.example.employeeservice.domain.model.Employee;
import com.example.employeeservice.domain.model.EmployeeSearchCriteria;
import com.example.employeeservice.domain.port.out.EmployeeQueryRepository;
import com.example.employeeservice.infrastructure.persistence.mapper.EmployeePersistenceMapper;
import com.example.employeeservice.infrastructure.persistence.repository.EmployeeJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseEmployeeQueryRepository implements EmployeeQueryRepository {

    private final EmployeeJpaRepository employeeJpaRepository;
    private final EmployeePersistenceMapper mapper;

    public DatabaseEmployeeQueryRepository(EmployeeJpaRepository employeeJpaRepository, EmployeePersistenceMapper mapper) {
        this.employeeJpaRepository = employeeJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Employee> findByCriteria(EmployeeSearchCriteria criteria) {
        var jpaCriteria = mapper.toJpaCriteria(criteria);
        var entities = employeeJpaRepository.findByCriteria(jpaCriteria);

        return entities.stream()
                .map(mapper::toDomain)
                .toList();
    }
}