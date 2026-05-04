package com.possr.repositories.possr.admin;

import org.springframework.stereotype.Repository;

import com.possr.entities.possr.admin.GenreEntity;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
    @Transactional
    @Query(value = """
        SELECT * FROM genres WHERE id = :id LIMIT 1
    """, nativeQuery = true)
    GenreEntity getGenreById(Long id);
}
