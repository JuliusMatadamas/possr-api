package com.possr.utils.evals;

import org.springframework.stereotype.Component;

import com.possr.constants.AppMessages;
import com.possr.dto.EmployeeDTO;
import com.possr.utils.Logging;

@Component
public class EmployeeEval {
    private final Logging logging;
    private final EvalMethods evalMethods = new EvalMethods();

    public EmployeeEval(Logging logging) {
        this.logging = logging;
    }

    public void toCreateEmployee(EmployeeDTO employeeDTO) {
        validateEmployeeObject(employeeDTO);
        validateBasicInfo(employeeDTO);
        validateBirthdate(employeeDTO);
        validateIds(employeeDTO);
        validateOptionalFields(employeeDTO);
    }

    private void validateEmployeeObject(EmployeeDTO employeeDTO) {
        if (!evalMethods.isValidObject(employeeDTO)) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "The employee data is required", AppMessages.UNKNOWN_SOURCE, employeeDTO);
            throw new IllegalArgumentException("The employee data is required");
        }
    }

    private void validateBasicInfo(EmployeeDTO employeeDTO) {
        if (!evalMethods.isValidString(employeeDTO.getFirstname(), 2, 50)) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "The employee firstname is required (2-50 characters)", AppMessages.UNKNOWN_SOURCE, employeeDTO);
            throw new IllegalArgumentException("The employee firstname is required (2-50 characters)");
        }

        if (!evalMethods.isValidString(employeeDTO.getLastname(), 2, 50)) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "The employee lastname is required (2-50 characters)", AppMessages.UNKNOWN_SOURCE, employeeDTO);
            throw new IllegalArgumentException("The employee lastname is required (2-50 characters)");
        }
    }

    private void validateBirthdate(EmployeeDTO employeeDTO) {
        if (employeeDTO.getBirthdate() != null && !evalMethods.isValidDate(employeeDTO.getBirthdate())) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "The employee birthdate must be a valid date (YYYY-MM-DD)", AppMessages.UNKNOWN_SOURCE, employeeDTO);
            throw new IllegalArgumentException("The employee birthdate must be a valid date (YYYY-MM-DD)");
        }

        if (employeeDTO.getBirthdate() != null && !evalMethods.isMinimumAge(employeeDTO.getBirthdate(), 18)) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "The employee must be at least 18 years old", AppMessages.UNKNOWN_SOURCE, employeeDTO);
            throw new IllegalArgumentException("The employee must be at least 18 years old");
        }
    }

    private void validateIds(EmployeeDTO employeeDTO) {
        if (!evalMethods.isValidLong(employeeDTO.getGenreId())) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "The employee genre ID is required", AppMessages.UNKNOWN_SOURCE, employeeDTO);
            throw new IllegalArgumentException("The employee genre ID is required");
        }

        if (!evalMethods.isValidLong(employeeDTO.getNeighborhoodId())) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "The employee neighborhood ID is required", AppMessages.UNKNOWN_SOURCE, employeeDTO);
            throw new IllegalArgumentException("The employee neighborhood ID is required");
        }
    }

    private void validateOptionalFields(EmployeeDTO employeeDTO) {
        if (employeeDTO.getCurp() != null && !evalMethods.isValidString(employeeDTO.getCurp(), 18, 18)) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "The employee CURP must be exactly 18 characters", AppMessages.UNKNOWN_SOURCE, employeeDTO);
            throw new IllegalArgumentException("The employee CURP must be exactly 18 characters");
        }

        if (employeeDTO.getNss() != null && !evalMethods.isValidString(employeeDTO.getNss(), 1, 11)) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "The employee NSS must be between 1 and 11 characters", AppMessages.UNKNOWN_SOURCE, employeeDTO);
            throw new IllegalArgumentException("The employee NSS must be between 1 and 11 characters");
        }

        if (employeeDTO.getRfc() != null && !evalMethods.isValidString(employeeDTO.getRfc(), 10, 13)) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "The employee RFC must be between 10 and 13 characters", AppMessages.UNKNOWN_SOURCE, employeeDTO);
            throw new IllegalArgumentException("The employee RFC must be between 10 and 13 characters");
        }

        if (employeeDTO.getPersonalPhone() != null && !evalMethods.isValidString(employeeDTO.getPersonalPhone(), 10, 20)) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "The employee personal phone must be between 10 and 20 characters", AppMessages.UNKNOWN_SOURCE, employeeDTO);
            throw new IllegalArgumentException("The employee personal phone must be between 10 and 20 characters");
        }

        if (employeeDTO.getPersonalEmail() != null && !evalMethods.isValidEmail(employeeDTO.getPersonalEmail())) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "The employee personal email must be a valid email", AppMessages.UNKNOWN_SOURCE, employeeDTO);
            throw new IllegalArgumentException("The employee personal email must be a valid email");
        }

        if (employeeDTO.getAddress() != null && !evalMethods.isValidString(employeeDTO.getAddress(), 1, 100)) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "The employee address must be between 1 and 100 characters", AppMessages.UNKNOWN_SOURCE, employeeDTO);
            throw new IllegalArgumentException("The employee address must be between 1 and 100 characters");
        }
    }
}
