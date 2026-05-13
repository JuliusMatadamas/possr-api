package com.possr.controllers;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.possr.constants.AppMessages;
import com.possr.dto.ApiResponseDTO;
import com.possr.dto.CompanyDTO;
import com.possr.services.CompanyService;
import com.possr.utils.Logging;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyController {
    private final Logging logging;
    private final CompanyService companyService;

    @PostMapping("/v1/create")
    public CompletableFuture<ResponseEntity<ApiResponseDTO>> createCompany(@RequestBody CompanyDTO companyDTO) {
        logging.logInfo(AppMessages.TO_CREATE_COMPANY, "Calling service to create company", AppMessages.UNKNOWN_SOURCE, companyDTO);
        return CompletableFuture.supplyAsync(() -> companyService.createCompany(companyDTO));
    }
}
