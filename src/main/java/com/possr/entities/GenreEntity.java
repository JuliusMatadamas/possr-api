package com.possr.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "genres")
public class GenreEntity {
    @Id
    @Column(name = "genre_id")
    long id;

    @Column(name = "genre_shortname")
    String shortName;

    @Column(name = "genre_name")
    String name;
}
