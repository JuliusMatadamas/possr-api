package com.possr.services.admin.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.possr.constants.AppMessages;
import com.possr.dto.ApiResponseDTO;
import com.possr.dto.EmployeeDTO;
import com.possr.dto.MetaDTO;
import com.possr.mappers.admin.EmployeeMappers;
import com.possr.repositories.possr.admin.EmployeeRepository;
import com.possr.services.admin.EmployeeService;
import com.possr.utils.Logging;
import com.possr.utils.evals.EvalEmployee;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EvalEmployee evalEmployee;
    private final EmployeeRepository employeeRepository;
    private final EmployeeMappers employeeMapper;
    private final Logging logging;

    @Override
    public ResponseEntity<ApiResponseDTO> createEmployee(EmployeeDTO employeeDTO) {
        // 1. Validación de datos de entrada
        logging.logInfo(AppMessages.CREATE_EMPLOYEE_METHOD, "Validating employee data", AppMessages.UNKNOWN_SOURCE, employeeDTO);
        evalEmployee.toCreate(employeeDTO);

        // 2. Mapear a entity el employeeDTO
        logging.logInfo(AppMessages.CREATE_EMPLOYEE_METHOD, "Mapping employee data to entity", AppMessages.UNKNOWN_SOURCE, employeeDTO);
        var employeeEntity = employeeMapper.toEntity(employeeDTO);

        // 3. Guardar el empleado
        try {
            logging.logInfo(AppMessages.CREATE_EMPLOYEE_METHOD, "Saving employee to database", AppMessages.UNKNOWN_SOURCE, employeeDTO);
            int rowsAffected = employeeRepository.createEmployee(employeeEntity);
            if (rowsAffected == 0) {
                logging.logError(AppMessages.CREATE_EMPLOYEE_METHOD, "No rows were affected when creating the employee in the database", AppMessages.UNKNOWN_SOURCE, employeeDTO);
                throw new IllegalArgumentException("No rows were affected when creating the employee in the database");
            }
        } catch (Exception ex) {
            logging.logError(AppMessages.CREATE_EMPLOYEE_METHOD, ex.getMessage(), AppMessages.UNKNOWN_SOURCE, employeeDTO);
            throw new IllegalArgumentException("Error creating the employee in the database");
        }

        // 4. Construcción de respuesta
        MetaDTO meta = MetaDTO.builder()
                .status(AppMessages.SUCCESS)
                .statusCode(HttpStatus.CREATED.value())
                .message("Employee created successfully")
                .build();

        logging.logInfo(AppMessages.CREATE_EMPLOYEE_METHOD, "Employee created successfully", AppMessages.UNKNOWN_SOURCE, employeeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseDTO.builder()
                .meta(meta)
                .data(null)
                .build());
    }

}
