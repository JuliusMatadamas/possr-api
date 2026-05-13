package com.possr.services.impl;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.possr.constants.AppMessages;
import com.possr.dto.ApiResponseDTO;
import com.possr.dto.MetaDTO;
import com.possr.dto.RoleCompanyDTO;
import com.possr.exceptions.DatabaseCreationException;
import com.possr.repositories.RoleCompanyRepository;
import com.possr.services.RoleCompanyService;
import com.possr.utils.Logging;
import com.possr.utils.evals.RoleCompanyEval;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleCompanyServiceImpl implements RoleCompanyService {
    private final Logging logging;
    private final RoleCompanyEval roleCompanyEval;
    private final RoleCompanyRepository roleCompanyRepository;

    @Override
    public ResponseEntity<ApiResponseDTO> createRoleCompany(RoleCompanyDTO roleCompanyDTO) {
        // 1. SE evalua la información recibida
        roleCompanyEval.toCreateRoleCompany(roleCompanyDTO);

        // 2. Se registra la información en la base de datos
        logging.logInfo(AppMessages.TO_CREATE_ROLE_COMPANY, "Registering role company data", AppMessages.UNKNOWN_SOURCE, roleCompanyDTO);
        int result;
        try {
            result = roleCompanyRepository.createRoleCompany(
                roleCompanyDTO.getCompanyId(),
                roleCompanyDTO.getRole()
            );
        } catch (DataIntegrityViolationException e) {
            logging.logError(AppMessages.TO_CREATE_ROLE_COMPANY, "Error registering role company data: " + e.getMessage(), AppMessages.UNKNOWN_SOURCE, null);
            throw new DatabaseCreationException("Error registering role company data: Duplicate entry");
        } catch (Exception e) {
            logging.logError(AppMessages.TO_CREATE_ROLE_COMPANY, "Error registering role company data: " + e.getMessage(), AppMessages.UNKNOWN_SOURCE, null);
            throw new DatabaseCreationException("Error registering role company data");
        }

        // 3. Se construye la respuesta
        String message;
        String devMessage;
        HttpStatus httpStatus;

        if (result == 0) {
            message = "Error registering role company data";
            devMessage = "The query didn't return any result";
            httpStatus = HttpStatus.NOT_MODIFIED;
            logging.logWarning(AppMessages.TO_CREATE_ROLE_COMPANY, message, AppMessages.UNKNOWN_SOURCE, devMessage);
        } else {
            message = "Role company data registered successfully";
            devMessage = "The query returned a result";
            httpStatus = HttpStatus.CREATED;
            logging.logInfo(AppMessages.TO_CREATE_COMPANY, message, AppMessages.UNKNOWN_SOURCE, devMessage);
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
