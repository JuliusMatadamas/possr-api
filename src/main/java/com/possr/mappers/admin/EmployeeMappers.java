package com.possr.mappers.admin;

import org.springframework.stereotype.Component;

import com.possr.dto.EmployeeDTO;
import com.possr.entities.possr.admin.EmployeeEntity;

@Component
public class EmployeeMappers {
    public EmployeeDTO toDTO(EmployeeEntity entity) {
        return EmployeeDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstname())
                .lastName(entity.getLastname())
                .birthdate(entity.getBirthdate())
                .curp(entity.getCurp())
                .rfc(entity.getRfc())
                .genreId(entity.getGenreId())
                .personalEmail(entity.getPersonalEmail())
                .personalPhone(entity.getPersonalPhone())
                .address(entity.getAddress())
                .neighborhoodId(entity.getNeighborhoodId())
                .build();
    }

    public EmployeeEntity toEntity(EmployeeDTO dto) {
        return EmployeeEntity.builder()
                .id(dto.getId())
                .firstname(dto.getFirstName())
                .lastname(dto.getLastName())
                .birthdate(dto.getBirthdate())
                .curp(dto.getCurp())
                .rfc(dto.getRfc())
                .genreId(dto.getGenreId())
                .personalEmail(dto.getPersonalEmail())
                .personalPhone(dto.getPersonalPhone())
                .address(dto.getAddress())
                .neighborhoodId(dto.getNeighborhoodId())
                .build();
    }
}
