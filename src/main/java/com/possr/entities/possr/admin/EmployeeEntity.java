package com.possr.entities.possr.admin;

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
@Table(name = "employees")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname", nullable = false, length = 100)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 100)
    private String lastname;

    @Column(name = "birthdate")
    private String birthdate;

    @Column(name = "curp", nullable = false, length = 20)
    private String curp;

    @Column(name = "rfc", nullable = false, length = 15)
    private String rfc;

    @Column(name = "genre_id", nullable = false)
    private Long genreId;

    @Column(name = "personal_email", length = 100)
    private String personalEmail;

    @Column(name = "personal_phone", length = 20)
    private String personalPhone;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "neighborhood_id")
    private Long neighborhoodId;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "deleted_at")
    private String deletedAt;

    @Column(name = "updated_at")
    private String updatedAt;
}
