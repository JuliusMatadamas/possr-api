package com.possr.mappers.admin;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.possr.dto.WorkRelationDTO;
import com.possr.entities.possr.admin.WorkRelationEntity;

@Component
public class WorkRelationMapper {

    public WorkRelationEntity toEntity(WorkRelationDTO dto) {
        return WorkRelationEntity.builder()
                .employeeId(dto.getEmployeeId())
                .workRoleId(dto.getWorkRoleId())
                .workEmail(dto.getWorkEmail())
                .appUser(dto.getAppUser())
                .startingDate(LocalDate.parse(dto.getStartingDate()))
                .endingDate(dto.getEndingDate() != null ? LocalDate.parse(dto.getEndingDate()) : null)
                .build();
    }

    public WorkRelationDTO toDTO(WorkRelationEntity entity) {
        return WorkRelationDTO.builder()
                .employeeId(entity.getEmployeeId())
                .workRoleId(entity.getWorkRoleId())
                .workEmail(entity.getWorkEmail())
                .appUser(entity.getAppUser())
                .startingDate(entity.getStartingDate() != null ? entity.getStartingDate().toString() : null)
                .endingDate(entity.getEndingDate() != null ? entity.getEndingDate().toString() : null)
                .build();
    }
}
