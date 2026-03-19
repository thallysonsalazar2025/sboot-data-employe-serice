package com.example.employeeservice.infrastructure.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class LoggingConfiguration {

    @Bean
    public Filter requestResponseLoggingFilter() {
        return new RequestResponseLoggingFilter();
    }

    @Slf4j
    static class RequestResponseLoggingFilter implements Filter {

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                throws IOException, ServletException {
            var httpRequest = (HttpServletRequest) request;
            var httpResponse = (HttpServletResponse) response;

            log.info("HTTP request started. method={} path={}", httpRequest.getMethod(), httpRequest.getRequestURI());
            chain.doFilter(request, response);
            log.info("HTTP request finished. method={} path={} status={}",
                    httpRequest.getMethod(),
                    httpRequest.getRequestURI(),
                    httpResponse.getStatus());
        }
    }
}
