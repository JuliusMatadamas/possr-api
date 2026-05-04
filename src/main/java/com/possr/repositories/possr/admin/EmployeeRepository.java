package com.possr.repositories.possr.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.possr.entities.possr.admin.EmployeeEntity;

import jakarta.transaction.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO employees (
            id,
            firstname,
            lastname,
            birthdate,
            curp,
            rfc,
            genre_id,
            personal_email,
            personal_phone,
            address,
            neighborhood_id,
            created_at,
            deleted_at,
            updated_at
        )
        VALUES (
            :#{#employee.id},
            :#{#employee.firstname},
            :#{#employee.lastname},
            :#{#employee.birthdate},
            :#{#employee.curp},
            :#{#employee.rfc},
            :#{#employee.genreId},
            :#{#employee.personalEmail},
            :#{#employee.personalPhone},
            :#{#employee.address},
            :#{#employee.neighborhoodId},
            CURRENT_TIMESTAMP(),
            NULL,
            CURRENT_TIMESTAMP()
        )
    """, nativeQuery = true)
    int createEmployee(@Param("employee") EmployeeEntity employee);
}
