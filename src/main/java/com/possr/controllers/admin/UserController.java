package com.possr.controllers.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.possr.dto.ApiResponseDTO;
import com.possr.services.admin.UsersService;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class UserController {
    private final UsersService usersService;

    @GetMapping("/v1/all-users")
    public CompletableFuture<ResponseEntity<ApiResponseDTO>> getAllUsers() {
        return CompletableFuture.supplyAsync(usersService::getAllUsers);
    }
}
