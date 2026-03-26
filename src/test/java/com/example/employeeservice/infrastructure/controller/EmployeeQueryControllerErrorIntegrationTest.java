package com.example.employeeservice.infrastructure.controller;

import com.example.employeeservice.domain.port.in.SearchEmployeeDataUseCase;
import com.example.employeeservice.infrastructure.mapper.EmployeeSearchMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeQueryController.class)
class EmployeeQueryControllerErrorIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SearchEmployeeDataUseCase searchEmployeeDataUseCase;

    @MockBean
    private EmployeeSearchMapper employeeSearchMapper;

    @Test
    void shouldReturnInternalServerErrorWhenUseCaseThrowsUnexpectedException() throws Exception {
        doThrow(new RuntimeException("boom"))
                .when(searchEmployeeDataUseCase)
                .search(any());

        mockMvc.perform(post("/api/v1/employees/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "tenantId": "tenant-a",
                                  "correlationId": "corr-it-5",
                                  "registrationNumber": "REG-100"
                                }
                                """))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("Unexpected internal error"));
    }
}
