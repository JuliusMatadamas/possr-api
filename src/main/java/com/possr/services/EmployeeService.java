package com.possr.services;

import org.springframework.http.ResponseEntity;

import com.possr.dto.ApiResponseDTO;
import com.possr.dto.EmployeeDTO;

public interface EmployeeService {
    ResponseEntity<ApiResponseDTO> createEmployee(EmployeeDTO employeeDTO);
}
