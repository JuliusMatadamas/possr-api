package com.possr.services.admin;

import org.springframework.http.ResponseEntity;

import com.possr.dto.ApiResponseDTO;
import com.possr.dto.UserDTO;

public interface UsersService {
    ResponseEntity<ApiResponseDTO> getAllUsers();
    ResponseEntity<ApiResponseDTO> createUser(UserDTO userDTO);
}
