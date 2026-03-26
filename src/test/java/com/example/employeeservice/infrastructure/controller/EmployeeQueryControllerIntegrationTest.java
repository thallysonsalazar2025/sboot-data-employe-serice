package com.example.employeeservice.infrastructure.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {
        "spring.jpa.defer-datasource-initialization=true",
        "spring.sql.init.mode=always"
})
@AutoConfigureMockMvc
@Transactional
class EmployeeQueryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnEmployeesByTenantAndRegistrationNumber() throws Exception {
        mockMvc.perform(post("/api/v1/employees/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "tenantId": "46634044000174",
                                  "correlationId": "corr-it-1",
                                  "registrationNumber": "REG-001"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tenantId").value("46634044000174"))
                .andExpect(jsonPath("$.correlationId").value("corr-it-1"))
                .andExpect(jsonPath("$.totalRecords").value(1))
                .andExpect(jsonPath("$.employees[0].fullName").value("João Silva"));
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

    @Test
    void shouldReturnBadRequestWhenPayloadIsMalformed() throws Exception {
        mockMvc.perform(post("/api/v1/employees/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"invalid\" \"json\""))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Malformed request payload"));
    }

    @Test
    void shouldReturnBadRequestWhenStatusEnumIsInvalid() throws Exception {
        mockMvc.perform(post("/api/v1/employees/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "tenantId": "tenant-a",
                                  "correlationId": "corr-it-4",
                                  "status": "UNKNOWN"
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Malformed request payload"));
    }
}
