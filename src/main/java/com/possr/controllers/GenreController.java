package com.possr.controllers;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.possr.constants.AppMessages;
import com.possr.dto.ApiResponseDTO;
import com.possr.dto.GenreDTO;
import com.possr.services.GenreService;
import com.possr.utils.Logging;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genres")
public class GenreController {
    private final Logging logging;
    private final GenreService genreService;

    @PostMapping("/v1/create")
    public CompletableFuture<ResponseEntity<ApiResponseDTO>> createGenre(@RequestBody GenreDTO genreDTO) {
        logging.logInfo(AppMessages.TO_CREATE_GENRE, "Calling service to create genre", AppMessages.UNKNOWN_SOURCE, genreDTO);
        return CompletableFuture.supplyAsync(() -> genreService.createGenre(genreDTO));
    }
}
