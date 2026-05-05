package com.possr.repositories.possr.admin;

import org.springframework.stereotype.Repository;

import com.possr.entities.possr.admin.WorkRelationEntity;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface WorkRelationRepository extends JpaRepository<WorkRelationEntity, Long> {
    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO work_relations
            (
                employee_id,
                work_role_id,
                work_email,
                app_user,
                starting_date,
                ending_date,
                created_at,
                deleted_at,
                updated_at
            )
        VALUES (
                :#{#workRelation.employeeId},
                :#{#workRelation.workRoleId},
                :#{#workRelation.workEmail},
                :#{#workRelation.appUser},
                :#{#workRelation.startingDate},
                :#{#workRelation.endingDate},
                NOW(),
                NULL,
                NOW()
            )
    """, nativeQuery = true)
    int createWorkRelation(@Param("workRelation") WorkRelationEntity workRelationEntity);
}
