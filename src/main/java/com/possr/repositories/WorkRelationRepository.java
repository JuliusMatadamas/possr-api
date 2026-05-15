package com.possr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.possr.entities.WorkRelationEntity;

import jakarta.transaction.Transactional;

@Repository
public interface WorkRelationRepository extends JpaRepository<WorkRelationEntity, Long> {
    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO work_relations
        (
            role_company_id,
            employee_id,
            email,
            user_app,
            starting_date,
            ending_date,
            created_at,
            deleted_at,
            updated_at
        )
        VALUES
        (
            :roleCompanyId,
            :employeeId,
            :email,
            :userApp,
            :startingDate,
            :endingDate,
            NOW(),
            NULL,
            NOW()
        )
    """, nativeQuery = true)
    int createWorkRelation(
        @Param("roleCompanyId") Long roleCompanyId,
        @Param("employeeId") Long employeeId,
        @Param("email") String email,
        @Param("userApp") Boolean userApp,
        @Param("startingDate") java.time.LocalDate startingDate,
        @Param("endingDate") java.time.LocalDate endingDate
    );
}
