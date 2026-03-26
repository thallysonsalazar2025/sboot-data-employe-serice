package com.example.employeeservice.infrastructure.persistence.repository;

import com.example.employeeservice.infrastructure.persistence.dto.EmployeeJpaCriteria;
import com.example.employeeservice.infrastructure.persistence.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeJpaRepository extends JpaRepository<EmployeeEntity, Long> {

    @Query("SELECT e FROM EmployeeEntity e " +
           "WHERE (:#{#criteria.tenantId} IS NULL OR e.company.registrationNumber = :#{#criteria.tenantId}) " +
           "AND (:#{#criteria.employeeId} IS NULL OR CAST(e.id AS string) = :#{#criteria.employeeId}) " +
           "AND (:#{#criteria.registrationNumber} IS NULL OR e.registrationNumber = :#{#criteria.registrationNumber}) " +
           "AND (:#{#criteria.documentNumber} IS NULL OR e.documentNumber = :#{#criteria.documentNumber}) " +
           "AND (:#{#criteria.status} IS NULL OR e.status = :#{#criteria.status})")
    List<EmployeeEntity> findByCriteria(
            @Param("criteria") EmployeeJpaCriteria criteria
    );
}