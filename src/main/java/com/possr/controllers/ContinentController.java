package com.possr.controllers;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.possr.constants.AppMessages;
import com.possr.dto.ApiResponseDTO;
import com.possr.services.ContinentService;
import com.possr.utils.Logging;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/continents")
public class ContinentController {
	private final Logging logging;
	private final ContinentService continentService;

	@GetMapping("/v1/getAll")
	public CompletableFuture<ResponseEntity<ApiResponseDTO>> getAllContinents() {
        logging.logInfo(AppMessages.GET_ALL_CONTINENTS, "Calling service to get all continents", AppMessages.UNKNOWN_SOURCE, null);
        return CompletableFuture.supplyAsync(() -> continentService.getAll());
    }
}
