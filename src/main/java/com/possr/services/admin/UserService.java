package com.possr.services.admin;

import org.springframework.http.ResponseEntity;

import com.possr.dto.ApiResponseDTO;
import com.possr.dto.UserToCreateDTO;

public interface UserService {
    ResponseEntity<ApiResponseDTO> getAllUsers();
    ResponseEntity<ApiResponseDTO> createUser(UserToCreateDTO userToCreateDTO);
}
