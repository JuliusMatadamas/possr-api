package com.possr.services.admin.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.possr.constants.AppMessages;
import com.possr.dto.ApiResponseDTO;
import com.possr.dto.MetaDTO;
import com.possr.dto.UserDTO;
import com.possr.mappers.admin.UserMapper;
import com.possr.repositories.possr.admin.UserRepository;
import com.possr.services.admin.UsersService;
import com.possr.utils.evals.EvalUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final EvalUser evalUser;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<ApiResponseDTO> getAllUsers() {
        throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
    }

    @Override
    public ResponseEntity<ApiResponseDTO> createUser(UserDTO userDTO) {
        // 1. Validación de datos de entrada
        evalUser.toCreate(userDTO);

        // 2. Mapear a entity el userDTO
        var userEntity = userMapper.toEntity(userDTO);

        // 3. Encriptar la contraseña
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        // 4. Guardar el usuario
        try {
            int rowsAffected = userRepository.createUser(userEntity);
            if (rowsAffected == 0) {
                throw new IllegalArgumentException("No rows were affected when creating the user in the database");
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException("Error creating the user in the database");

            // Sistema de logging aquí
        }

        // 5. Construcción de respuesta
        MetaDTO meta = MetaDTO.builder()
                .status(AppMessages.SUCCESS)
                .statusCode(HttpStatus.CREATED.value())
                .message("User created successfully")
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseDTO.builder()
                .meta(meta)
                .data(null)
                .build());
    }

}
