package com.possr.services.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.possr.constants.AppMessages;
import com.possr.dto.ApiResponseDTO;
import com.possr.dto.ContinentDTO;
import com.possr.dto.MetaDTO;
import com.possr.entities.ContinentEntity;
import com.possr.exceptions.DatabaseCreationException;
import com.possr.mappers.ContinentMapper;
import com.possr.repositories.ContinentRepository;
import com.possr.services.ContinentService;
import com.possr.utils.Logging;
import com.possr.utils.evals.ContinentEval;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContinentServiceImpl implements ContinentService {
	private final Logging logging;
	private final ContinentEval continentEval;
	private final ContinentRepository continentRepository;
    private final ContinentMapper continentMapper;

	@Override
	public ResponseEntity<ApiResponseDTO> getAll() {
		// 1. Se obtiene la información de los continents en la BD
        logging.logInfo(AppMessages.GET_ALL_CONTINENTS, "Retrieving all continents", AppMessages.UNKNOWN_SOURCE, null);
        List<ContinentDTO> data;
        List<ContinentEntity> result;
        try {
            result = continentRepository.getAll();
            data = result.stream().map(continentMapper::toDTO).toList();
        } catch (Exception e) {
            logging.logError(AppMessages.GET_ALL_CONTINENTS, "Error retrieving all continents: " + e.getMessage(), AppMessages.UNKNOWN_SOURCE, null);
            throw new DatabaseCreationException("Error retrieving all continents");
        }

        // 2. Se construye la respuesta
        String message;
        String devMessage;
        HttpStatus httpStatus;

        if (data.isEmpty()) {
            message = "Error retrieving all continents";
            devMessage = "The query didn't return any result";
            httpStatus = HttpStatus.NO_CONTENT;
            logging.logWarning(AppMessages.GET_ALL_CONTINENTS, message, AppMessages.UNKNOWN_SOURCE, devMessage);
        } else {
            message = "Continents retrieved successfully";
            devMessage = "The query returned a result";
            httpStatus = HttpStatus.OK;
            logging.logInfo(AppMessages.GET_ALL_CONTINENTS, message, AppMessages.UNKNOWN_SOURCE, devMessage);
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

	@Override
	public ResponseEntity<ApiResponseDTO> create(ContinentDTO continentDTO) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'create'");
	}

	@Override
	public ResponseEntity<ApiResponseDTO> update(ContinentDTO continentDTO) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'update'");
	}

	@Override
	public ResponseEntity<ApiResponseDTO> delete(Long id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'delete'");
	}

	@Override
	public ResponseEntity<ApiResponseDTO> getById(Long id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getById'");
	}

	@Override
	public ResponseEntity<ApiResponseDTO> getByPartialName(String name) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getByPartialName'");
	}

}
