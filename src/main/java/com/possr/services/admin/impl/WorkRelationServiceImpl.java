package com.possr.services.admin.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.possr.constants.AppMessages;
import com.possr.dto.ApiResponseDTO;
import com.possr.dto.MetaDTO;
import com.possr.dto.WorkRelationDTO;
import com.possr.mappers.admin.WorkRelationMapper;
import com.possr.repositories.possr.admin.WorkRelationRepository;
import com.possr.services.admin.WorkRelationService;
import com.possr.utils.Logging;
import com.possr.utils.evals.EvalWorkRelation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkRelationServiceImpl implements WorkRelationService {
    private final EvalWorkRelation evalWorkRelation;
    private final WorkRelationRepository workRelationRepository;
    private final Logging logging;
    private final WorkRelationMapper workRelationMapper;

    @Override
    public ResponseEntity<ApiResponseDTO> createWorkRelation(WorkRelationDTO workRelationDTO) {
        // 1. Validación de datos de entrada
        logging.logInfo(AppMessages.CREATE_WORK_RELATION, "Validating work relation data", AppMessages.UNKNOWN_SOURCE, workRelationDTO);
        evalWorkRelation.toCreate(workRelationDTO);

        // 2. Mapear a entity el workRelationDTO
        logging.logInfo(AppMessages.CREATE_WORK_RELATION, "Mapping work relation data to entity", AppMessages.UNKNOWN_SOURCE, workRelationDTO);
        var workRelationEntity = workRelationMapper.toEntity(workRelationDTO);

        // 3. Guardar el work relation
        try {
            logging.logInfo(AppMessages.CREATE_WORK_RELATION, "Saving work relation to database", AppMessages.UNKNOWN_SOURCE, workRelationDTO);
            int rowsAffected = workRelationRepository.createWorkRelation(workRelationEntity);
            if (rowsAffected == 0) {
                logging.logError(AppMessages.CREATE_WORK_RELATION, "No rows were affected when creating the work relation in the database", AppMessages.UNKNOWN_SOURCE, workRelationDTO);
                throw new IllegalArgumentException("No rows were affected when creating the work relation in the database");
            }
        } catch (Exception ex) {
            logging.logError(AppMessages.CREATE_WORK_RELATION, ex.getMessage(), AppMessages.UNKNOWN_SOURCE, workRelationDTO);
            throw new IllegalArgumentException("Error creating the work relation in the database");
        }

        // 5. Construcción de respuesta
        MetaDTO meta = MetaDTO.builder()
                .status(AppMessages.SUCCESS)
                .statusCode(HttpStatus.CREATED.value())
                .message("Work relation created successfully")
                .build();

        logging.logInfo(AppMessages.CREATE_WORK_RELATION, "Work relation created successfully", AppMessages.UNKNOWN_SOURCE, workRelationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseDTO.builder()
                .meta(meta)
                .data(null)
                .build());
    }

}
