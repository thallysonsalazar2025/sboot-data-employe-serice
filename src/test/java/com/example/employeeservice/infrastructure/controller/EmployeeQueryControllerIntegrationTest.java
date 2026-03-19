package com.example.employeeservice.infrastructure.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeQueryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnEmployeesByTenantAndRegistrationNumber() throws Exception {
        mockMvc.perform(post("/api/v1/employees/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "tenantId": "tenant-a",
                                  "correlationId": "corr-it-1",
                                  "registrationNumber": "REG-100"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tenantId").value("tenant-a"))
                .andExpect(jsonPath("$.correlationId").value("corr-it-1"))
                .andExpect(jsonPath("$.totalRecords").value(1))
                .andExpect(jsonPath("$.employees[0].fullName").value("Ana Souza"));
    }

    @Test
    void shouldReturnValidationErrorWhenFilterIsMissing() throws Exception {
        mockMvc.perform(post("/api/v1/employees/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "tenantId": "tenant-a",
                                  "correlationId": "corr-it-2"
                                }
                                """))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message").value("At least one search filter must be informed"));
    }

    @Test
    void shouldReturnBadRequestWhenTenantIsMissing() throws Exception {
        mockMvc.perform(post("/api/v1/employees/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "correlationId": "corr-it-3",
                                  "registrationNumber": "REG-100"
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation error"))
                .andExpect(jsonPath("$.details[0]").value("tenantId: tenantId is required"));
    }
}
