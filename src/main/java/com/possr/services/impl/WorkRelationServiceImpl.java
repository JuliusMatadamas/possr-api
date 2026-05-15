package com.possr.services.impl;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.possr.constants.AppMessages;
import com.possr.dto.ApiResponseDTO;
import com.possr.dto.WorkRelationDTO;
import com.possr.dto.MetaDTO;
import com.possr.exceptions.DatabaseCreationException;
import com.possr.repositories.WorkRelationRepository;
import com.possr.services.WorkRelationService;
import com.possr.utils.Logging;
import com.possr.utils.evals.WorkRelationEval;
import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkRelationServiceImpl implements WorkRelationService {
    private final Logging logging;
    private final WorkRelationEval workRelationEval;
    private final WorkRelationRepository workRelationRepository;

    @Override
    public ResponseEntity<ApiResponseDTO> createWorkRelation(WorkRelationDTO workRelationDTO) {
        // 1. Se valida la información recibida de la relación laboral
        logging.logInfo(AppMessages.WORK_RELATION_TO_CREATE, "Validating work relation data", AppMessages.UNKNOWN_SOURCE, workRelationDTO);
        workRelationEval.toCreateWorkRelation(workRelationDTO);

        // 2. Se registra la información de la relación laboral en la BD
        logging.logInfo(AppMessages.WORK_RELATION_TO_CREATE, "Registering work relation data", AppMessages.UNKNOWN_SOURCE, workRelationDTO);
        int result;
        try {
            java.time.LocalDate startingDate = java.time.LocalDate.parse(workRelationDTO.getStartingDate());
            java.time.LocalDate endingDate = workRelationDTO.getEndingDate() != null
                ? java.time.LocalDate.parse(workRelationDTO.getEndingDate())
                : null;

            result = workRelationRepository.createWorkRelation(
                workRelationDTO.getRoleCompanyId(),
                workRelationDTO.getEmployeeId(),
                workRelationDTO.getEmail(),
                workRelationDTO.getUserApp(),
                startingDate,
                endingDate
            );
        } catch (DataIntegrityViolationException e) {
            logging.logError(AppMessages.WORK_RELATION_TO_CREATE, "Error registering work relation data: " + e.getMessage(), AppMessages.UNKNOWN_SOURCE, null);
            throw new DatabaseCreationException("Error registering work relation data: Duplicate entry");
        } catch (Exception e) {
            logging.logError(AppMessages.WORK_RELATION_TO_CREATE, "Error registering work relation data: " + e.getMessage(), AppMessages.UNKNOWN_SOURCE, null);
            throw new DatabaseCreationException("Error registering work relation data");
        }

        // 3. Se construye la respuesta
        String message;
        String devMessage;
        HttpStatus httpStatus;

        if (result == 0) {
            message = "Error registering work relation data";
            devMessage = "The query didn't return any result";
            httpStatus = HttpStatus.NOT_MODIFIED;
            logging.logWarning(AppMessages.WORK_RELATION_TO_CREATE, message, AppMessages.UNKNOWN_SOURCE, devMessage);
        } else {
            message = "Work relation data registered successfully";
            devMessage = "The query returned a result";
            httpStatus = HttpStatus.CREATED;
            logging.logInfo(AppMessages.WORK_RELATION_TO_CREATE, message, AppMessages.UNKNOWN_SOURCE, devMessage);
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
}
