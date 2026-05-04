package com.possr.repositories.possr.admin;

import org.springframework.stereotype.Repository;

import com.possr.entities.possr.admin.NeighborhoodEntity;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface NeighborhoodRepository extends JpaRepository<NeighborhoodEntity, Long> {
    @Transactional
    @Query(value = """
        SELECT * FROM neighborhoods WHERE id = :id LIMIT 1
    """, nativeQuery = true)
    NeighborhoodEntity getNeighborhoodById(Long id);
}
