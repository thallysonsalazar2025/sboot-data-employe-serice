package com.example.employeeservice.infrastructure.persistence;

import com.example.employeeservice.domain.model.Employee;
import com.example.employeeservice.domain.model.EmployeeSearchCriteria;
import com.example.employeeservice.domain.model.EmploymentStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryEmployeeQueryRepositoryTest {

    private final InMemoryEmployeeQueryRepository repository = new InMemoryEmployeeQueryRepository();

    @Test
    void shouldFilterByTenantAndRegistration() {
        var criteria = new EmployeeSearchCriteria("tenant-a", "corr-1", null, "REG-100", null, null);

        var result = repository.findByCriteria(criteria);

        assertThat(result).extracting(Employee::id).containsExactly("1");
    }

    @Test
    void shouldFilterByStatus() {
        var criteria = new EmployeeSearchCriteria("tenant-a", "corr-2", null, null, null, EmploymentStatus.LEAVE);

        var result = repository.findByCriteria(criteria);

        assertThat(result)
                .hasSize(1)
                .first()
                .extracting(Employee::status)
                .isEqualTo(EmploymentStatus.LEAVE);
    }

    @Test
    void shouldReturnEmptyWhenTenantDoesNotMatch() {
        var criteria = new EmployeeSearchCriteria("unknown", "corr-3", null, null, null, null);

        var result = repository.findByCriteria(criteria);

        assertThat(result).isEmpty();
    }
}
