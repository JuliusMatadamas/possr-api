package com.possr.controllers;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.possr.constants.AppMessages;
import com.possr.dto.ApiResponseDTO;
import com.possr.dto.LoginDTO;
import com.possr.services.AuthService;
import com.possr.utils.Logging;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final Logging logging;
    private final AuthService authService;

    @PostMapping("/v1/login")
    public CompletableFuture<ResponseEntity<ApiResponseDTO>> login(@RequestBody LoginDTO loginDTO) {
        logging.logInfo("AuthController.login", "Login", AppMessages.UNKNOWN_SOURCE, loginDTO);
        return CompletableFuture.supplyAsync(() -> authService.login(loginDTO));
    }
}
