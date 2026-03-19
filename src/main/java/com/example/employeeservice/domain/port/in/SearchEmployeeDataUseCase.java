package com.example.employeeservice.domain.port.in;

import com.example.employeeservice.application.dto.EmployeeSearchResponse;
import com.example.employeeservice.domain.model.EmployeeSearchCriteria;

public interface SearchEmployeeDataUseCase {

    EmployeeSearchResponse search(EmployeeSearchCriteria criteria);
}
