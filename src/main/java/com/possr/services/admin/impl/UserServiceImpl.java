package com.possr.services.admin.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.possr.constants.AppMessages;
import com.possr.dto.ApiResponseDTO;
import com.possr.dto.MetaDTO;
import com.possr.dto.UserToCreateDTO;
import com.possr.mappers.admin.UserMapper;
import com.possr.repositories.possr.admin.UserRepository;
import com.possr.services.admin.UserService;
import com.possr.utils.Logging;
import com.possr.utils.evals.EvalUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final EvalUser evalUser;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final Logging logging;

    @Override
    public ResponseEntity<ApiResponseDTO> getAllUsers() {
        throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
    }

    @Override
    public ResponseEntity<ApiResponseDTO> createUser(UserToCreateDTO userToCreateDTO) {
        // 1. Validación de datos de entrada
        logging.logInfo(AppMessages.CREATE_USER_METHOD, "Validating user data", AppMessages.UNKNOWN_SOURCE, userToCreateDTO);
        evalUser.toCreate(userToCreateDTO);

        // 2. Mapear a entity el userDTO
        logging.logInfo(AppMessages.CREATE_USER_METHOD, "Mapping user data to entity", AppMessages.UNKNOWN_SOURCE, userToCreateDTO);
        var userEntity = userMapper.toEntity(userToCreateDTO);

        // 3. Encriptar la contraseña
        logging.logInfo(AppMessages.CREATE_USER_METHOD, "Encrypting user password", AppMessages.UNKNOWN_SOURCE, userToCreateDTO);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        // 4. Guardar el usuario
        try {
            logging.logInfo(AppMessages.CREATE_USER_METHOD, "Saving user to database", AppMessages.UNKNOWN_SOURCE, userToCreateDTO);
            int rowsAffected = userRepository.createUser(userEntity);
            if (rowsAffected == 0) {
                logging.logError(AppMessages.CREATE_USER_METHOD, "No rows were affected when creating the user in the database", AppMessages.UNKNOWN_SOURCE, userToCreateDTO);
                throw new IllegalArgumentException("No rows were affected when creating the user in the database");
            }
        } catch (Exception ex) {
            logging.logError(AppMessages.CREATE_USER_METHOD, ex.getMessage(), AppMessages.UNKNOWN_SOURCE, userToCreateDTO);
            throw new IllegalArgumentException("Error creating the user in the database");
        }

        // 5. Construcción de respuesta
        MetaDTO meta = MetaDTO.builder()
                .status(AppMessages.SUCCESS)
                .statusCode(HttpStatus.CREATED.value())
                .message("User created successfully")
                .build();

        logging.logInfo(AppMessages.CREATE_USER_METHOD, "User created successfully", AppMessages.UNKNOWN_SOURCE, userToCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseDTO.builder()
                .meta(meta)
                .data(null)
                .build());
    }

}
