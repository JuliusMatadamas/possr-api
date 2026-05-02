package com.possr.services.admin;

import org.springframework.http.ResponseEntity;

import com.possr.dto.ApiResponseDTO;

public interface UsersService {
    ResponseEntity<ApiResponseDTO> getAllUsers();
}
