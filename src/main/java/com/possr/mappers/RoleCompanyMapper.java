package com.possr.mappers;

import com.possr.dto.RoleCompanyDTO;
import com.possr.entities.RoleCompanyEntity;

import org.springframework.stereotype.Component;

@Component
public class RoleCompanyMapper {

    public RoleCompanyEntity toEntity(RoleCompanyDTO roleCompanyDTO) {
        RoleCompanyEntity entity = new RoleCompanyEntity();
        if (roleCompanyDTO.getRoleCompanyId() != null) {
            entity.setId(roleCompanyDTO.getRoleCompanyId());
        }
        entity.setCompanyId(roleCompanyDTO.getCompanyId());
        entity.setRole(roleCompanyDTO.getRole());
        return entity;
    }

    public RoleCompanyDTO toDto(RoleCompanyEntity roleCompanyEntity) {
        return RoleCompanyDTO.builder()
                .roleCompanyId(roleCompanyEntity.getId())
                .companyId(roleCompanyEntity.getCompanyId())
                .role(roleCompanyEntity.getRole())
                .build();
    }
}
