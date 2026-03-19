package com.example.employeeservice.application.usecase;

import com.example.employeeservice.application.dto.EmployeeSearchResponse;
import com.example.employeeservice.application.mapper.EmployeeResponseMapper;
import com.example.employeeservice.application.validator.EmployeeSearchValidator;
import com.example.employeeservice.domain.model.EmployeeSearchCriteria;
import com.example.employeeservice.domain.port.in.SearchEmployeeDataUseCase;
import com.example.employeeservice.domain.port.out.EmployeeQueryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SearchEmployeeDataService implements SearchEmployeeDataUseCase {

    private final EmployeeQueryRepository employeeQueryRepository;
    private final EmployeeSearchValidator validator;
    private final EmployeeResponseMapper mapper;

    public SearchEmployeeDataService(EmployeeQueryRepository employeeQueryRepository,
                                     EmployeeSearchValidator validator,
                                     EmployeeResponseMapper mapper) {
        this.employeeQueryRepository = employeeQueryRepository;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public EmployeeSearchResponse search(EmployeeSearchCriteria criteria) {
        validator.validate(criteria);

        log.info("Searching employees for tenant={} correlationId={}", criteria.tenantId(), criteria.correlationId());

        var employees = employeeQueryRepository.findByCriteria(criteria)
                .stream()
                .map(mapper::toResponse)
                .toList();

        log.info("Search completed for tenant={} correlationId={} totalRecords={}",
                criteria.tenantId(),
                criteria.correlationId(),
                employees.size());

        return new EmployeeSearchResponse(
                criteria.tenantId(),
                criteria.correlationId(),
                employees.size(),
                employees
        );
    }
}
