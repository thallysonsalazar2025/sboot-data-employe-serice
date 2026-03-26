package com.example.employeeservice.infrastructure.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = EmployeeApiProperties.PREFIX)
public record EmployeeApiProperties(
        String basePath,
        String searchPath
) {
    public static final String PREFIX = "employee-service.api";
    public static final String BASE_PATH_PROPERTY = PREFIX + ".base-path";
    public static final String SEARCH_PATH_PROPERTY = PREFIX + ".search-path";
}
