package com.example.employeeservice.application;

import com.example.employeeservice.application.mapper.EmployeeResponseMapper;
import com.example.employeeservice.application.usecase.SearchEmployeeDataService;
import com.example.employeeservice.application.validator.EmployeeSearchValidator;
import com.example.employeeservice.domain.model.Employee;
import com.example.employeeservice.domain.model.EmployeeSearchCriteria;
import com.example.employeeservice.domain.model.EmploymentStatus;
import com.example.employeeservice.domain.port.out.EmployeeQueryRepository;
import com.example.employeeservice.infrastructure.exception.BusinessValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchEmployeeDataServiceTest {

    @Mock
    private EmployeeQueryRepository repository;

    private SearchEmployeeDataService service;

    @BeforeEach
    void setUp() {
        service = new SearchEmployeeDataService(repository, new EmployeeSearchValidator(), new EmployeeResponseMapper());
    }

    @Test
    void shouldReturnEmployeesForTenantAwareSearch() {
        var criteria = new EmployeeSearchCriteria("tenant-a", "corr-1", null, "REG-100", null, null);
        when(repository.findByCriteria(criteria)).thenReturn(List.of(
                new Employee("1", "tenant-a", "REG-100", "Ana Souza", "12345678901", EmploymentStatus.ACTIVE, "Finance", "PAY-001")
        ));

        var response = service.search(criteria);

        assertThat(response.tenantId()).isEqualTo("tenant-a");
        assertThat(response.correlationId()).isEqualTo("corr-1");
        assertThat(response.totalRecords()).isEqualTo(1);
        assertThat(response.employees()).hasSize(1);
        assertThat(response.employees().getFirst().fullName()).isEqualTo("Ana Souza");
        verify(repository).findByCriteria(criteria);
    }

    @Test
    void shouldRejectRequestWithoutAnyFilter() {
        var criteria = new EmployeeSearchCriteria("tenant-a", "corr-1", null, null, null, null);

        assertThatThrownBy(() -> service.search(criteria))
                .isInstanceOf(BusinessValidationException.class)
                .hasMessage("At least one search filter must be informed");
    }
}
