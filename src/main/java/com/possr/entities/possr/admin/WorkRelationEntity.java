package com.possr.entities.possr.admin;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "work_relations")
public class WorkRelationEntity {

    @Id
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "work_role_id", nullable = false)
    private Long workRoleId;

    @Column(name = "work_email", nullable = false, length = 100)
    private String workEmail;

    @Column(name = "app_user", nullable = false)
    @Builder.Default
    private Boolean appUser = true;

    @Column(name = "starting_date")
    private LocalDate startingDate;

    @Column(name = "ending_date")
    private LocalDate endingDate;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "deleted_at")
    private LocalDate deletedAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
