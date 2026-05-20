package com.possr.mappers;

import java.time.LocalDate;

import com.possr.dto.EmployeeDTO;
import com.possr.entities.EmployeeEntity;

import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeEntity toEntity(EmployeeDTO employeeDTO) {
        EmployeeEntity entity = new EmployeeEntity();
        entity.setFirstname(employeeDTO.getFirstname());
        entity.setLastname(employeeDTO.getLastname());

        if (employeeDTO.getBirthdate() != null) {
            entity.setBirthdate(LocalDate.parse(employeeDTO.getBirthdate()));
        }

        entity.setGenreId(employeeDTO.getGenreId());
        entity.setCurp(employeeDTO.getCurp());
        entity.setNss(employeeDTO.getNss());
        entity.setRfc(employeeDTO.getRfc());
        entity.setPersonalPhone(employeeDTO.getPersonalPhone());
        entity.setPersonalEmail(employeeDTO.getPersonalEmail());
        entity.setAddress(employeeDTO.getAddress());
        entity.setNeighborhoodId(employeeDTO.getNeighborhoodId());

        return entity;
    }

    public EmployeeDTO toDTO(EmployeeEntity entity) {
        if (entity == null) {
            return null;
        }

        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(entity.getId());
        dto.setFirstname(entity.getFirstname());
        dto.setLastname(entity.getLastname());

        if (entity.getBirthdate() != null) {
            dto.setBirthdate(entity.getBirthdate().toString());
        } else {
            dto.setBirthdate(null);
        }

        dto.setGenreId(entity.getGenreId());
        dto.setCurp(entity.getCurp());
        dto.setNss(entity.getNss());
        dto.setRfc(entity.getRfc());
        dto.setPersonalPhone(entity.getPersonalPhone());
        dto.setPersonalEmail(entity.getPersonalEmail());
        dto.setAddress(entity.getAddress());
        dto.setNeighborhoodId(entity.getNeighborhoodId());

        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setDeletedAt(entity.getDeletedAt());

        return dto;
    }
}
