package com.example.employeeservice.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "job_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobRoleEntity {

    @Id
    private Long id;

    private String title; // Ex: Analista de Sistemas, Médico
    private String cbo;   // Código Brasileiro de Ocupações (Opcional, mas útil)

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;
}