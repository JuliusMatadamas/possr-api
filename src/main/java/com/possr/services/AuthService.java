package com.possr.services;

import org.springframework.http.ResponseEntity;

import com.possr.dto.ApiResponseDTO;
import com.possr.dto.LoginDTO;

public interface AuthService {
    ResponseEntity<ApiResponseDTO> login(LoginDTO loginDTO);
}
