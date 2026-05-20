package com.possr.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.possr.constants.AppMessages;
import com.possr.dto.ApiResponseDTO;
import com.possr.dto.EmployeeDTO;
import com.possr.dto.MetaDTO;
import com.possr.entities.EmployeeEntity;
import com.possr.exceptions.DatabaseCreationException;
import com.possr.mappers.EmployeeMapper;
import com.possr.repositories.EmployeeRepository;
import com.possr.services.EmployeeService;
import com.possr.utils.Logging;
import com.possr.utils.evals.EmployeeEval;
import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final Logging logging;
    private final EmployeeEval employeeEval;
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public ResponseEntity<ApiResponseDTO> createEmployee(EmployeeDTO employeeDTO) {
        // 1. Se valida la información recibida del empleado
        logging.logInfo(AppMessages.EMPLOYEE_TO_CREATE, "Validating employee data", AppMessages.UNKNOWN_SOURCE, employeeDTO);
        employeeEval.toCreateEmployee(employeeDTO);

        // 2. Se registra la información del empleado en la BD
        logging.logInfo(AppMessages.EMPLOYEE_TO_CREATE, "Registering employee data", AppMessages.UNKNOWN_SOURCE, employeeDTO);
        int result;
        try {
            java.time.LocalDate birthdate = employeeDTO.getBirthdate() != null
                ? java.time.LocalDate.parse(employeeDTO.getBirthdate())
                : null;

            result = employeeRepository.createEmployee(
                employeeDTO.getFirstname(),
                employeeDTO.getLastname(),
                birthdate,
                employeeDTO.getGenreId(),
                employeeDTO.getCurp(),
                employeeDTO.getNss(),
                employeeDTO.getRfc(),
                employeeDTO.getPersonalPhone(),
                employeeDTO.getPersonalEmail(),
                employeeDTO.getAddress(),
                employeeDTO.getNeighborhoodId()
            );
        } catch (DataIntegrityViolationException e) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "Error registering employee data: " + e.getMessage(), AppMessages.UNKNOWN_SOURCE, null);
            throw new DatabaseCreationException("Error registering employee data: Duplicate entry");
        } catch (Exception e) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "Error registering employee data: " + e.getMessage(), AppMessages.UNKNOWN_SOURCE, null);
            throw new DatabaseCreationException("Error registering employee data");
        }

        // 4. Se construye la respuesta
        String message;
        String devMessage;
        HttpStatus httpStatus;

        if (result == 0) {
            message = "Error registering employee data";
            devMessage = "The query didn't return any result";
            httpStatus = HttpStatus.NOT_MODIFIED;
            logging.logWarning(AppMessages.EMPLOYEE_TO_CREATE, message, AppMessages.UNKNOWN_SOURCE, devMessage);
        } else {
            message = "Employee data registered successfully";
            devMessage = "The query returned a result";
            httpStatus = HttpStatus.CREATED;
            logging.logInfo(AppMessages.EMPLOYEE_TO_CREATE, message, AppMessages.UNKNOWN_SOURCE, devMessage);
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
    public ResponseEntity<ApiResponseDTO> getAllEmployees() {
        // 1. Se inicia el proceso para obtener la lista de empleados registrados en el sistema
        logging.logInfo(AppMessages.GET_ALL_EMPLOYEES, "Getting all employees", AppMessages.UNKNOWN_SOURCE, null);
        List<EmployeeEntity> result = null;
        List<EmployeeDTO> employees = new ArrayList<>();

        try {
            result = employeeRepository.getAllEmployees();
        } catch (Exception e) {
            logging.logError(AppMessages.GET_ALL_EMPLOYEES, "Error retrieving employees: " + e.getMessage(), AppMessages.UNKNOWN_SOURCE, null);
            throw new DatabaseCreationException("Error retrieving employees");
        }

        // 2. Se construye la respuesta
        String message;
        String devMessage;
        HttpStatus httpStatus;

        if (result == null || result.isEmpty()) {
            message = "Error retrieving employees";
            devMessage = "The query didn't return any result";
            httpStatus = HttpStatus.NO_CONTENT;
            logging.logWarning(AppMessages.GET_ALL_EMPLOYEES, message, AppMessages.UNKNOWN_SOURCE, devMessage);
        } else {
            message = "Employees retrieved successfully";
            devMessage = "The query returned a result";
            httpStatus = HttpStatus.OK;
            for (EmployeeEntity entity : result) {
                employees.add(employeeMapper.toDTO(entity));
            }
            logging.logInfo(AppMessages.GET_ALL_EMPLOYEES, message, AppMessages.UNKNOWN_SOURCE, devMessage);
        }

        MetaDTO meta = MetaDTO.builder()
                .status(AppMessages.SUCCESS)
                .statusCode(httpStatus.value())
                .message(message)
                .devMessage(devMessage)
                .build();

        ApiResponseDTO response = ApiResponseDTO.builder()
                .meta(meta)
                .data(employees)
                .build();

        return ResponseEntity.status(httpStatus).body(response);
    }
}
