package com.possr.mappers.admin;

import org.springframework.stereotype.Component;

import com.possr.dto.UserDTO;
import com.possr.entities.possr.admin.UserEntity;

@Component
public class UserMapper {
    public UserDTO toDTO(UserEntity entity) {
        return UserDTO.builder()
                .id(entity.getId())
                .employeeId(entity.getEmployeeId())
                .roleId(entity.getRoleId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .build();
    }

    public UserEntity toEntity(UserDTO dto) {
        return UserEntity.builder()
                .employeeId(dto.getEmployeeId())
                .roleId(dto.getRoleId())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }
}
