package com.possr.services.impl;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.possr.constants.AppMessages;
import com.possr.dto.ApiResponseDTO;
import com.possr.dto.CompanyDTO;
import com.possr.dto.MetaDTO;
import com.possr.exceptions.DatabaseCreationException;
import com.possr.repositories.CompanyRepository;
import com.possr.services.CompanyService;
import com.possr.utils.Logging;
import com.possr.utils.evals.CompanyEval;
import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final Logging logging;
    private final CompanyEval companyEval;
    private final CompanyRepository companyRepository;

    @Override
    public ResponseEntity<ApiResponseDTO> createCompany(CompanyDTO companyDTO) {
        // 1. Se valida la información recibida de la empresa
        logging.logInfo(AppMessages.TO_CREATE_COMPANY, "Validating company data", AppMessages.UNKNOWN_SOURCE, companyDTO);
        companyEval.toCreateCompany(companyDTO);

        // 2. Se registra la información de la empresa en la BD
        logging.logInfo(AppMessages.TO_CREATE_COMPANY, "Registering company data", AppMessages.UNKNOWN_SOURCE, companyDTO);
        int result;
        try {
            result = companyRepository.createCompany(
                companyDTO.getShortName(),
                companyDTO.getLongName(),
                companyDTO.getRfc(),
                companyDTO.getAddress(),
                companyDTO.getNeighborhoodId()
            );
        } catch (DataIntegrityViolationException e) {
            logging.logError(AppMessages.TO_CREATE_COMPANY, "Error registering company data: " + e.getMessage(), AppMessages.UNKNOWN_SOURCE, null);
            throw new DatabaseCreationException("Error registering company data: Duplicate entry");
        } catch (Exception e) {
            logging.logError(AppMessages.TO_CREATE_COMPANY, "Error registering company data: " + e.getMessage(), AppMessages.UNKNOWN_SOURCE, null);
            throw new DatabaseCreationException("Error registering company data");
        }

        // 3. Se construye la respuesta
        String message;
        String devMessage;
        HttpStatus httpStatus;

        if (result == 0) {
            message = "Error registering company data";
            devMessage = "The query didn't return any result";
            httpStatus = HttpStatus.NOT_MODIFIED;
            logging.logWarning(AppMessages.TO_CREATE_COMPANY, message, AppMessages.UNKNOWN_SOURCE, devMessage);
        } else {
            message = "Company data registered successfully";
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
