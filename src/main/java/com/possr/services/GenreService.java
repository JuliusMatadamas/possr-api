package com.possr.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.possr.dto.ApiResponseDTO;
import com.possr.dto.GenreDTO;

@Service
public interface GenreService {
    ResponseEntity<ApiResponseDTO> createGenre(GenreDTO genreDTO);

    ResponseEntity<ApiResponseDTO> getAll();
}
