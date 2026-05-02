package com.possr.services.admin.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.possr.dto.ApiResponseDTO;
import com.possr.services.admin.UsersService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    @Override
    public ResponseEntity<ApiResponseDTO> getAllUsers() {
        throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
    }

}
