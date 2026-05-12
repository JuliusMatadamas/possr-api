package com.possr.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.possr.dto.ApiResponseDTO;
import com.possr.dto.CompanyDTO;

@Service
public interface CompanyService {
    ResponseEntity<ApiResponseDTO> createCompany(CompanyDTO companyDTO);
}
