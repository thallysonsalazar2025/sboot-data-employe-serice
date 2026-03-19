package com.example.employeeservice.application.validator;

import com.example.employeeservice.domain.model.EmployeeSearchCriteria;
import com.example.employeeservice.domain.model.EmploymentStatus;
import com.example.employeeservice.infrastructure.exception.BusinessValidationException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EmployeeSearchValidatorTest {

    private final EmployeeSearchValidator validator = new EmployeeSearchValidator();

    @Test
    void shouldPassWhenAtLeastOneFilterProvided() {
        var criteria = new EmployeeSearchCriteria("tenant-a", "corr-1", null, null, null, EmploymentStatus.ACTIVE);

        assertThatCode(() -> validator.validate(criteria)).doesNotThrowAnyException();
    }

    @Test
    void shouldFailWhenNoFilterProvided() {
        var criteria = new EmployeeSearchCriteria("tenant-a", "corr-1", null, null, null, null);

        assertThatThrownBy(() -> validator.validate(criteria))
                .isInstanceOf(BusinessValidationException.class)
                .hasMessage("At least one search filter must be informed");
    }

    @Test
    void shouldFailWhenDocumentNumberHasInvalidLength() {
        var criteria = new EmployeeSearchCriteria("tenant-a", "corr-1", null, null, "123", null);

        assertThatThrownBy(() -> validator.validate(criteria))
                .isInstanceOf(BusinessValidationException.class)
                .hasMessage("documentNumber must contain 11 or 14 digits");
    }
}
