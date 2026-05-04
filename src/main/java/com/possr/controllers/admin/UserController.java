package com.possr.controllers.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.possr.dto.ApiResponseDTO;
import com.possr.dto.UserDTO;
import com.possr.services.admin.UsersService;
import com.possr.utils.Logging;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class UserController {
    private final UsersService usersService;
    private final Logging logging;

    @GetMapping("/v1/all-users")
    public CompletableFuture<ResponseEntity<ApiResponseDTO>> getAllUsers() {
        return CompletableFuture.supplyAsync(usersService::getAllUsers);
    }

    @PostMapping("/v1/create-user")
    public CompletableFuture<ResponseEntity<ApiResponseDTO>> createUser(
            @RequestBody UserDTO userDTO) {
        logging.logInfo("UserController.createUser", "Creating user", "UserController", userDTO);
        return CompletableFuture.supplyAsync(() -> usersService.createUser(userDTO));
    }
}
