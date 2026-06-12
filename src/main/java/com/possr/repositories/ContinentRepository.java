package com.possr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.possr.entities.ContinentEntity;

import jakarta.transaction.Transactional;

@Repository
public interface ContinentRepository extends JpaRepository<ContinentEntity, Long> {
	@Transactional
	@Query(value = """
		select
			c.continent_id,
			c.continent_name,
			c.created_at,
			c.deleted_at,
			c.updated_at 
		from
			possr.continents c
	""", nativeQuery = true)
	List<ContinentEntity> getAll();
    
}
