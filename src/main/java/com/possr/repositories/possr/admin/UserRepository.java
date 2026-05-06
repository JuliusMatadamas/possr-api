package com.possr.repositories.possr.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.possr.entities.possr.admin.UserToCreateEntity;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserToCreateEntity, Long> {
    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO users (employee_id, role_id, username, password)
        SELECT
            :#{#user.employeeId} AS employee_id,
            :#{#user.roleId} AS role_id,
            :#{#user.username} AS username,
            :#{#user.password} AS password
        FROM (SELECT 1 AS validation_result) AS dummy
        WHERE
            EXISTS (SELECT 1 FROM employees WHERE id = :#{#user.employeeId})
            AND EXISTS (SELECT 1 FROM app_roles WHERE id = :#{#user.roleId})
            AND EXISTS (SELECT 1 FROM work_relations WHERE employee_id = :#{#user.employeeId})
            AND EXISTS (
                SELECT 1 FROM (
                    SELECT * FROM work_relations wr
                    WHERE employee_id = :#{#user.employeeId}
                    ORDER BY COALESCE(wr.updated_at, wr.created_at) DESC
                    LIMIT 1
                ) AS latest_wr
                WHERE latest_wr.starting_date IS NOT NULL
            )
            AND EXISTS (
                SELECT 1 FROM (
                    SELECT * FROM work_relations wr
                    WHERE employee_id = :#{#user.employeeId}
                    ORDER BY COALESCE(wr.updated_at, wr.created_at) DESC
                    LIMIT 1
                ) AS latest_wr
                WHERE latest_wr.ending_date IS NULL OR latest_wr.ending_date > CURDATE()
            )
            AND EXISTS (
                SELECT 1 FROM (
                    SELECT * FROM work_relations wr
                    WHERE employee_id = :#{#user.employeeId}
                    ORDER BY COALESCE(wr.updated_at, wr.created_at) DESC
                    LIMIT 1
                ) AS latest_wr
                WHERE latest_wr.app_user = true
            )
    """, nativeQuery = true)
    int createUser(@Param("user") UserToCreateEntity userToCreateEntity);

    @Transactional
    @Query(value = """
        SELECT * FROM users WHERE username = :username LIMIT 1
    """, nativeQuery = true)
    UserToCreateEntity findByUsername(@Param("username") String username);
}

