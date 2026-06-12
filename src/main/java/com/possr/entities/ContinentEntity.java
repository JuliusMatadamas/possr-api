package com.possr.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "continents")
public class ContinentEntity {
    @Id
    @Column(name = "continent_id")
    long id;

    @Column(name = "continent_name", nullable = false)
    String name;

    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDateTime createdAt;

    @Column(name = "deleted_at")
    LocalDateTime deletedAt;

    @Column(name = "updated_at", nullable = false)
    LocalDateTime updatedAt;
}
