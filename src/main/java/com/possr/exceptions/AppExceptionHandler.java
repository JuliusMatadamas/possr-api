package com.possr.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.possr.dto.ApiResponseDTO;
import com.possr.dto.MetaDTO;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO> handleAllExceptions(Exception ex) {
        MetaDTO meta = MetaDTO.builder()
                .status("ERROR")
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Ocurrió un error interno en el servidor.")
                .devMessage(ex.getMessage())
                .build();

        ApiResponseDTO response = ApiResponseDTO.builder()
                .meta(meta)
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
