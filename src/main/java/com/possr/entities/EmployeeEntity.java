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
@Table(name = "employees")
public class EmployeeEntity {
    @Id
    @Column(name = "employee_id")
    long id;

    @Column(name = "firstname", nullable = false)
    String firstname;

    @Column(name = "lastname", nullable = false)
    String lastname;

    @Column(name = "birthdate")
    LocalDate birthdate;

    @Column(name = "genre_id", nullable = false)
    long genreId;

    @Column(name = "curp")
    String curp;

    @Column(name = "nss")
    String nss;

    @Column(name = "rfc")
    String rfc;

    @Column(name = "personal_phone")
    String personalPhone;

    @Column(name = "personal_email")
    String personalEmail;

    @Column(name = "address")
    String address;

    @Column(name = "neighborhood_id", nullable = false)
    long neighborhoodId;

    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDateTime createdAt;

    @Column(name = "deleted_at")
    LocalDateTime deletedAt;

    @Column(name = "updated_at", nullable = false)
    LocalDateTime updatedAt;
}
