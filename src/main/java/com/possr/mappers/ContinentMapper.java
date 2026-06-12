package com.possr.mappers;

import com.possr.dto.ContinentDTO;
import com.possr.entities.ContinentEntity;

import org.springframework.stereotype.Component;

@Component
public class ContinentMapper {

    public ContinentEntity toEntity(
            ContinentDTO continentDTO
    ) {
        if (continentDTO == null) {
            return null;
        }

        ContinentEntity entity = new ContinentEntity();
        if (continentDTO.getId() != null) {
            entity.setId(
                    continentDTO.getId()
            );
        }
        entity.setName(
                continentDTO.getName()
        );

        return entity;
    }

    public ContinentDTO toDTO(
            ContinentEntity continentEntity
    ) {
        if (continentEntity == null) {
            return null;
        }

        return ContinentDTO.builder()
                .id(
                        continentEntity.getId()
                )
                .name(
                        continentEntity.getName()
                )
                .build();
    }
}
