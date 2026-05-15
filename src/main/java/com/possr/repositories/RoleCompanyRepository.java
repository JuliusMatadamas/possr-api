package com.possr.repositories;

import org.springframework.stereotype.Repository;

import com.possr.entities.RoleCompanyEntity;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface RoleCompanyRepository extends JpaRepository<RoleCompanyEntity, Long> {
    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO roles_companies
        (
            company_id,
            role,
            created_at,
            deleted_at,
            updated_at
        )
        VALUES
        (
            :companyId,
            :role,
            NOW(),
            NULL,
            NOW()
        )
    """, nativeQuery = true)
    int createRoleCompany(long companyId, String role);

    @Transactional
    @Query(value = """
        SELECT
            rc.role_company_id,
            rc.company_id,
            rc.`role`
        FROM
            possr.roles_companies rc
        WHERE
            rc.company_id = :companyId
        AND
            rc.deleted_at IS NULL
    """, nativeQuery = true)
    List<RoleCompanyEntity> getAllRoleCompanyByCompanyId(long companyId);
}
