package com.possr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.possr.entities.CompanyEntity;

import jakarta.transaction.Transactional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO companies
        (
            company_shortname,
            company_longname,
            company_rfc,
            address,
            neighborhood_id,
            created_at,
            deleted_at,
            updated_at
        )
        VALUES
        (
            :shortName,
            :longName,
            :rfc,
            :address,
            :neighborhoodId,
            NOW(),
            NULL,
            NOW()
        )
    """, nativeQuery = true)
    int createCompany(
        @Param("shortName") String shortName,
        @Param("longName") String longName,
        @Param("rfc") String rfc,
        @Param("address") String address,
        @Param("neighborhoodId") Long neighborhoodId
    );
}
