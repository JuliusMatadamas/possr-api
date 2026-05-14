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
}
