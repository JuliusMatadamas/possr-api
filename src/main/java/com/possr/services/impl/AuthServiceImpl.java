package com.possr.services.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

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
    private final Logging logging;

    @Override
    public ResponseEntity<ApiResponseDTO> login(LoginDTO loginDTO) {
        // 1. Validación de datos de entrada
        logging.logInfo(AppMessages.TO_LOGIN, "Validating login data", AppMessages.UNKNOWN_SOURCE, loginDTO);
        evalLogin.toLogin(loginDTO);

        return null;
    }
}
