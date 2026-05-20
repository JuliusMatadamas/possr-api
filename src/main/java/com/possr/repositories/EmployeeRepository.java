package com.possr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.possr.entities.EmployeeEntity;

import jakarta.transaction.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO employees
        (
            firstname,
            lastname,
            birthdate,
            genre_id,
            curp,
            nss,
            rfc,
            personal_phone,
            personal_email,
            address,
            neighborhood_id,
            created_at,
            deleted_at,
            updated_at
        )
        VALUES
        (
            :firstname,
            :lastname,
            :birthdate,
            :genreId,
            :curp,
            :nss,
            :rfc,
            :personalPhone,
            :personalEmail,
            :address,
            :neighborhoodId,
            NOW(),
            NULL,
            NOW()
        )
    """, nativeQuery = true)
    int createEmployee(
        @Param("firstname") String firstname,
        @Param("lastname") String lastname,
        @Param("birthdate") java.time.LocalDate birthdate,
        @Param("genreId") Long genreId,
        @Param("curp") String curp,
        @Param("nss") String nss,
        @Param("rfc") String rfc,
        @Param("personalPhone") String personalPhone,
        @Param("personalEmail") String personalEmail,
        @Param("address") String address,
        @Param("neighborhoodId") Long neighborhoodId
    );

    @Transactional
    @Query(value = """
        SELECT
            e.employee_id,
            e.firstname,
            e.lastname,
            e.birthdate,
            e.genre_id,
            e.curp,
            e.nss,
            e.rfc,
            e.personal_phone,
            e.personal_email,
            e.address,
            e.neighborhood_id,
            e.created_at,
            e.deleted_at,
            e.updated_at
        FROM
            possr.employees e
    """, nativeQuery = true)
    List<EmployeeEntity> getAllEmployees();
}
