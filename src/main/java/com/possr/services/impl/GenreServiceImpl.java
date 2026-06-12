package com.possr.services.impl;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.possr.constants.AppMessages;
import com.possr.dto.ApiResponseDTO;
import com.possr.dto.GenreDTO;
import com.possr.dto.MetaDTO;
import com.possr.entities.GenreEntity;
import com.possr.exceptions.DatabaseCreationException;
import com.possr.mappers.GenreMapper;
import com.possr.repositories.GenreRepository;
import com.possr.services.GenreService;
import com.possr.utils.Logging;
import com.possr.utils.evals.GenreEval;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final Logging logging;
    private final GenreEval genreEval;
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public ResponseEntity<ApiResponseDTO> createGenre(GenreDTO genreDTO) {
        // 1. Se valida la información recibida del género
        logging.logInfo(AppMessages.TO_CREATE_GENRE, "Validating genre data", AppMessages.UNKNOWN_SOURCE, genreDTO);
        genreEval.toCreateGenre(genreDTO);

        // 2. Se registra la información de la empresa en la BD
        logging.logInfo(AppMessages.TO_CREATE_GENRE, "Registering genre data", AppMessages.UNKNOWN_SOURCE, genreDTO);
        int result;
        try {
            result = genreRepository.createGenre(
                genreDTO.getShortName(),
                genreDTO.getName()
            );
        } catch (DataIntegrityViolationException e) {
            logging.logError(AppMessages.TO_CREATE_GENRE, "Error registering genre data: " + e.getMessage(), AppMessages.UNKNOWN_SOURCE, null);
            throw new DatabaseCreationException("Error registering genre data: Duplicate entry");
        } catch (Exception e) {
            logging.logError(AppMessages.TO_CREATE_GENRE, "Error registering genre data: " + e.getMessage(), AppMessages.UNKNOWN_SOURCE, null);
            throw new DatabaseCreationException("Error registering genre data");
        }

        // 3. Se construye la respuesta
        String message;
        String devMessage;
        HttpStatus httpStatus;

        if (result == 0) {
            message = "Error registering genre data";
            devMessage = "The query didn't return any result";
            httpStatus = HttpStatus.NOT_MODIFIED;
            logging.logWarning(AppMessages.TO_CREATE_GENRE, message, AppMessages.UNKNOWN_SOURCE, devMessage);
        } else {
            message = "Genre data registered successfully";
            devMessage = "The query returned a result";
            httpStatus = HttpStatus.CREATED;
            logging.logInfo(AppMessages.TO_CREATE_GENRE, message, AppMessages.UNKNOWN_SOURCE, devMessage);
        }

        MetaDTO meta = MetaDTO.builder()
                .status(AppMessages.SUCCESS)
                .statusCode(httpStatus.value())
                .message(message)
                .devMessage(devMessage)
                .build();

        ApiResponseDTO response = ApiResponseDTO.builder()
                .meta(meta)
                .data(null)
                .build();

        return ResponseEntity.status(httpStatus).body(response);
    }

    @Override
    public ResponseEntity<ApiResponseDTO> getAll() {
        // 1. Se obtiene la información de los géneros en la BD
        logging.logInfo(AppMessages.GET_ALL_GENRES, "Getting genre data", AppMessages.UNKNOWN_SOURCE, null);
        List<GenreEntity> result;
        List<GenreDTO> data;
        try {
            result = genreRepository.getAll();
            data = result.stream().map(genreMapper::toDTO).toList();
        } catch (Exception e) {
            logging.logError(AppMessages.GET_ALL_GENRES, "Error getting genre data: " + e.getMessage(), AppMessages.UNKNOWN_SOURCE, null);
            throw new DatabaseCreationException("Error getting genre data");
        }

        // 2. Se construye la respuesta
        String message;
        String devMessage;
        HttpStatus httpStatus;

        if (data.isEmpty()) {
            message = "Error getting genre data";
            devMessage = "The query didn't return any result";
            httpStatus = HttpStatus.NO_CONTENT;
            logging.logWarning(AppMessages.GET_ALL_GENRES, message, AppMessages.UNKNOWN_SOURCE, devMessage);
        } else {
            message = "Genre data registered successfully";
            devMessage = "The query returned a result";
            httpStatus = HttpStatus.CREATED;
            logging.logInfo(AppMessages.TO_CREATE_GENRE, message, AppMessages.UNKNOWN_SOURCE, devMessage);
        }

        MetaDTO meta = MetaDTO.builder()
                .status(AppMessages.SUCCESS)
                .statusCode(httpStatus.value())
                .message(message)
                .devMessage(devMessage)
                .build();

        ApiResponseDTO response = ApiResponseDTO.builder()
                .meta(meta)
                .data(data)
                .build();

        return ResponseEntity.status(httpStatus).body(response);
    }

}
