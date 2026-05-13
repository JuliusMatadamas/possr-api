package com.possr.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.possr.dto.ApiResponseDTO;
import com.possr.dto.RoleCompanyDTO;

@Service
public interface RoleCompanyService {
    ResponseEntity<ApiResponseDTO> createRoleCompany(RoleCompanyDTO roleCompanyDTO);
}
