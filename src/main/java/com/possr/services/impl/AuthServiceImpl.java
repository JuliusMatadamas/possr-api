package com.possr.services.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

import com.possr.config.JwtProperties;
import com.possr.constants.AppMessages;
import com.possr.dto.ApiResponseDTO;
import com.possr.dto.LoginDTO;
import com.possr.entities.possr.UserAppEntity;
import com.possr.mappers.UserAppMapper;
import com.possr.repositories.possr.AuthRepository;
import com.possr.services.AuthService;
import com.possr.utils.Logging;
import com.possr.utils.evals.EvalLogin;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final EvalLogin evalLogin;
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final Logging logging;
    private final UserAppMapper userAppMapper;
    private final JwtProperties jwtProperties;

    @Override
    public ResponseEntity<ApiResponseDTO> login(LoginDTO loginDTO) {
        // 1. Validación de datos de entrada
        logging.logInfo(AppMessages.TO_LOGIN, "Validating login data", AppMessages.UNKNOWN_SOURCE, loginDTO);
        evalLogin.toLogin(loginDTO);

        // 2. Verificar credenciales
        UserAppEntity userAppEntity = null;
        try {
            logging.logInfo(AppMessages.TO_LOGIN, "Verifying credentials", AppMessages.UNKNOWN_SOURCE, loginDTO);
            userAppEntity = authRepository.findByUsernameAndEmployeeId(loginDTO.getUsername().trim(), loginDTO.getEmployeeId());

            if (userAppEntity == null) {
                logging.logError(AppMessages.TO_LOGIN, "These credentials do not match our records", AppMessages.UNKNOWN_SOURCE, loginDTO);
                throw new IllegalArgumentException("These credentials do not match our records");
            }

            if (!passwordEncoder.matches(loginDTO.getPassword(), userAppEntity.getPassword())) {
                logging.logError(AppMessages.TO_LOGIN, "These credentials do not match our records", AppMessages.UNKNOWN_SOURCE, loginDTO);
                throw new IllegalArgumentException("These credentials do not match our records");
            }

            logging.logInfo(AppMessages.TO_LOGIN, "User credentials verified successfully", AppMessages.UNKNOWN_SOURCE, loginDTO);
        } catch (Exception ex) {
            logging.logError(AppMessages.TO_LOGIN, ex.getMessage(), AppMessages.UNKNOWN_SOURCE, loginDTO);
            throw new IllegalArgumentException("Error verifying credentials");
        }

        // 3. Generar token
        logging.logInfo(AppMessages.TO_LOGIN, "Generating JWT token", AppMessages.UNKNOWN_SOURCE, loginDTO);
        String token = generateJwtToken(userAppEntity);

        // 4. Crear sesión y retornar respuesta
        logging.logInfo(AppMessages.TO_LOGIN, "Creating session", AppMessages.UNKNOWN_SOURCE, loginDTO);
        authRepository.createSession(userAppEntity.getUserId(), token);

        var userAppDTO = userAppMapper.toDTO(userAppEntity, token);
        ApiResponseDTO response = ApiResponseDTO.builder()
                .data(userAppDTO)
                .build();

        logging.logInfo(AppMessages.TO_LOGIN, "Login completed successfully", AppMessages.UNKNOWN_SOURCE, loginDTO);
        return ResponseEntity.ok(response);
    }

    private String generateJwtToken(UserAppEntity userAppEntity) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiryDate = now.plusHours(jwtProperties.getExpirationHours());

        return Jwts.builder()
                .subject(userAppEntity.getIdCompany().toString())
                .claim("userId", userAppEntity.getIdCompany())
                .claim("employeeId", userAppEntity.getIdCompany())
                .claim("roleId", userAppEntity.getIdRole())
                .claim("workEmail", userAppEntity.getEmailWork())
                .issuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .expiration(Date.from(expiryDate.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(io.jsonwebtoken.security.Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes()))
                .compact();
    }

}
