package com.possr.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "work_relations")
public class WorkRelationEntity {
    @Id
    @Column(name = "work_relation_id")
    long id;

    @Column(name = "role_company_id", nullable = false)
    long roleCompanyId;

    @Column(name = "employee_id", nullable = false)
    long employeeId;

    @Column(name = "email", nullable = false, length = 100)
    String email;

    @Column(name = "user_app", nullable = false)
    Boolean userApp;

    @Column(name = "starting_date", nullable = false)
    LocalDate startingDate;

    @Column(name = "ending_date")
    LocalDate endingDate;

    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDateTime createdAt;

    @Column(name = "deleted_at")
    LocalDateTime deletedAt;

    @Column(name = "updated_at", nullable = false)
    LocalDateTime updatedAt;
}
