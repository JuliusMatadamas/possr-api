package com.possr.mappers;

import com.possr.dto.GenreDTO;
import com.possr.entities.GenreEntity;

import org.springframework.stereotype.Component;

@Component
public class GenreMapper {

    public GenreEntity toEntity(
            GenreDTO genreDTO
    ) {
        if (genreDTO == null) {
            return null;
        }

        GenreEntity entity = new GenreEntity();
        if (genreDTO.getGenreId() != null) {
            entity.setId(
                    genreDTO.getGenreId()
            );
        }
        entity.setShortName(
                genreDTO.getShortName()
        );
        entity.setName(
                genreDTO.getName()
        );

        return entity;
    }

    public GenreDTO toDTO(
            GenreEntity genreEntity
    ) {
        if (genreEntity == null) {
            return null;
        }

        return GenreDTO.builder()
                .genreId(
                        genreEntity.getId()
                )
                .shortName(
                        genreEntity.getShortName()
                )
                .name(
                        genreEntity.getName()
                )
                .build();
    }
}
