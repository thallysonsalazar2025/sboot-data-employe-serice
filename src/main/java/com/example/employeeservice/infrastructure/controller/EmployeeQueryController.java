package com.example.employeeservice.infrastructure.controller;

import com.example.employeeservice.application.dto.EmployeeSearchRequest;
import com.example.employeeservice.application.dto.EmployeeSearchResponse;
import com.example.employeeservice.domain.model.EmployeeSearchCriteria;
import com.example.employeeservice.domain.port.in.SearchEmployeeDataUseCase;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employees")
@Slf4j
public class EmployeeQueryController {

    private final SearchEmployeeDataUseCase searchEmployeeDataUseCase;

    public EmployeeQueryController(SearchEmployeeDataUseCase searchEmployeeDataUseCase) {
        this.searchEmployeeDataUseCase = searchEmployeeDataUseCase;
    }

    @PostMapping("/search")
    public ResponseEntity<EmployeeSearchResponse> search(@Valid @RequestBody EmployeeSearchRequest request) {
        log.info("Received employee search request from payroll orchestrator. tenant={} correlationId={}",
                request.tenantId(),
                request.correlationId());

        var criteria = new EmployeeSearchCriteria(
                request.tenantId(),
                request.correlationId(),
                request.employeeId(),
                request.registrationNumber(),
                request.documentNumber(),
                request.status()
        );

        return ResponseEntity.ok(searchEmployeeDataUseCase.search(criteria));
    }
}
