package com.possr.services;

import org.springframework.http.ResponseEntity;

import com.possr.dto.ApiResponseDTO;
import com.possr.dto.ContinentDTO;

public interface ContinentService {
	ResponseEntity<ApiResponseDTO> getAll();
	ResponseEntity<ApiResponseDTO> create(ContinentDTO continentDTO);
	ResponseEntity<ApiResponseDTO> update(ContinentDTO continentDTO);
	ResponseEntity<ApiResponseDTO> delete(Long id);
	ResponseEntity<ApiResponseDTO> getById(Long id);
	ResponseEntity<ApiResponseDTO> getByPartialName(String name);
}
