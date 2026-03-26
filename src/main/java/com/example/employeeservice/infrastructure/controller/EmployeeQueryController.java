package com.example.employeeservice.infrastructure.controller;

import com.example.employeeservice.application.dto.EmployeeSearchRequest;
import com.example.employeeservice.application.dto.EmployeeSearchResponse;
import com.example.employeeservice.domain.port.in.SearchEmployeeDataUseCase;
import com.example.employeeservice.infrastructure.config.properties.EmployeeApiProperties;
import com.example.employeeservice.infrastructure.mapper.EmployeeSearchMapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${" + EmployeeApiProperties.BASE_PATH_PROPERTY + "}")
@Slf4j
public class EmployeeQueryController {

    private final SearchEmployeeDataUseCase searchEmployeeDataUseCase;
    private final EmployeeSearchMapper employeeSearchMapper;

    public EmployeeQueryController(SearchEmployeeDataUseCase searchEmployeeDataUseCase, EmployeeSearchMapper employeeSearchMapper) {
        this.searchEmployeeDataUseCase = searchEmployeeDataUseCase;
        this.employeeSearchMapper = employeeSearchMapper;
    }

    @PostMapping("${" + EmployeeApiProperties.SEARCH_PATH_PROPERTY + "}")
    public ResponseEntity<EmployeeSearchResponse> search(@Valid @RequestBody EmployeeSearchRequest request) {
        log.info("Received employee search request from payroll orchestrator. tenant={} correlationId={}", request.tenantId(), request.correlationId());
        var criteria = employeeSearchMapper.toCriteria(request);

        return ResponseEntity.ok(searchEmployeeDataUseCase.search(criteria));
    }
}
