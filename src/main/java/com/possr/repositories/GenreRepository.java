package com.possr.repositories;

import org.springframework.stereotype.Repository;

import com.possr.entities.GenreEntity;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO genres
        (
            genre_shortname,
            genre_name,
            created_at,
            deleted_at,
            updated_at
        )
        VALUES
        (
            :shortName,
            :name,
            NOW(),
            NULL,
            NOW()
        )
    """, nativeQuery = true)
    int createGenre(
        @Param("shortName") String shortName,
        @Param("name") String name
    );

}
