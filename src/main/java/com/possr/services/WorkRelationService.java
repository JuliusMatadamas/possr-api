package com.possr.services;

import org.springframework.http.ResponseEntity;

import com.possr.dto.ApiResponseDTO;
import com.possr.dto.WorkRelationDTO;

public interface WorkRelationService {
    ResponseEntity<ApiResponseDTO> createWorkRelation(WorkRelationDTO workRelationDTO);
}
