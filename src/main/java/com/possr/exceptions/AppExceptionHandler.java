package com.possr.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.possr.dto.ApiResponseDTO;
import com.possr.dto.MetaDTO;
import com.possr.constants.AppMessages;

@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponseDTO> handleIllegalArgumentException(IllegalArgumentException ex) {
        MetaDTO meta = MetaDTO.builder()
                .status(AppMessages.ERROR)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message("Invalid request.")
                .devMessage(ex.getMessage())
                .build();

        ApiResponseDTO response = ApiResponseDTO.builder()
                .meta(meta)
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponseDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        MetaDTO meta = MetaDTO.builder()
                .status(AppMessages.ERROR)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message("The request body is required.")
                .devMessage(ex.getMessage())
                .build();

        ApiResponseDTO response = ApiResponseDTO.builder()
                .meta(meta)
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO> handleAllExceptions(Exception ex) {
        MetaDTO meta = MetaDTO.builder()
                .status(AppMessages.ERROR)
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("An internal server error has occurred.")
                .devMessage(ex.getMessage())
                .build();

        ApiResponseDTO response = ApiResponseDTO.builder()
                .meta(meta)
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
