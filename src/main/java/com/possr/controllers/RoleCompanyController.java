package com.possr.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.possr.constants.AppMessages;
import com.possr.dto.ApiResponseDTO;
import com.possr.dto.RoleCompanyDTO;
import com.possr.services.RoleCompanyService;
import com.possr.utils.Logging;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("/role-companies")
public class RoleCompanyController {
    private final Logging logging;
    private final RoleCompanyService roleCompanyService;

    @PostMapping("/v1/create")
    public CompletableFuture<ResponseEntity<ApiResponseDTO>> createRoleCompany(@RequestBody RoleCompanyDTO roleCompanyDTO) {
        logging.logInfo(AppMessages.TO_CREATE_ROLE_COMPANY, "Calling service to create role company", AppMessages.UNKNOWN_SOURCE, roleCompanyDTO);
        return CompletableFuture.supplyAsync(() -> roleCompanyService.createRoleCompany(roleCompanyDTO));
    }
}
