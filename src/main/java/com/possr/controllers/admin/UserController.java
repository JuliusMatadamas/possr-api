package com.possr.controllers.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.possr.constants.AppMessages;
import com.possr.dto.ApiResponseDTO;
import com.possr.dto.UserToCreateDTO;
import com.possr.services.admin.UserService;
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
    private final UserService userService;
    private final Logging logging;

    @GetMapping("/v1/all-users")
    public CompletableFuture<ResponseEntity<ApiResponseDTO>> getAllUsers() {
        return CompletableFuture.supplyAsync(userService::getAllUsers);
    }

    @PostMapping("/v1/create-user")
    public CompletableFuture<ResponseEntity<ApiResponseDTO>> createUser(
            @RequestBody UserToCreateDTO userToCreateDTO) {
        logging.logInfo("UserController.createUser", "Creating user", AppMessages.UNKNOWN_SOURCE, userToCreateDTO);
        return CompletableFuture.supplyAsync(() -> userService.createUser(userToCreateDTO));
    }
}
