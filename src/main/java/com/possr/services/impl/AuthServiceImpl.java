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
import com.possr.services.AuthService;
import com.possr.utils.Logging;
import com.possr.utils.evals.LoginEval;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final LoginEval evalLogin;
    private final PasswordEncoder passwordEncoder;
    private final Logging logging;
    private final JwtProperties jwtProperties;

    @Override
    public ResponseEntity<ApiResponseDTO> login(LoginDTO loginDTO) {
        // 1. Validación de datos de entrada
        logging.logInfo(AppMessages.TO_LOGIN, "Validating login data", AppMessages.UNKNOWN_SOURCE, loginDTO);
        evalLogin.toLogin(loginDTO);

        return null;
    }
}
