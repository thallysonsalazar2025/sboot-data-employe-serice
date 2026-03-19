package com.example.employeeservice.domain.port.out;

import com.example.employeeservice.domain.model.Employee;
import com.example.employeeservice.domain.model.EmployeeSearchCriteria;

import java.util.List;

public interface EmployeeQueryRepository {

    List<Employee> findByCriteria(EmployeeSearchCriteria criteria);
}
