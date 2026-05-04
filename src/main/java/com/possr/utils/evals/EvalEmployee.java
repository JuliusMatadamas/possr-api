package com.possr.utils.evals;

import org.springframework.stereotype.Component;

import com.possr.constants.AppMessages;
import com.possr.dto.EmployeeDTO;
import com.possr.repositories.possr.admin.GenreRepository;
import com.possr.repositories.possr.admin.NeighborhoodRepository;
import com.possr.utils.Logging;

@Component
public class EvalEmployee {
    private final Logging logging;
    private final GenreRepository genreRepository;
    private final NeighborhoodRepository neighborhoodRepository;
    private final EvalMethods evalMethods = new EvalMethods();

    public EvalEmployee(Logging logging, GenreRepository genreRepository, NeighborhoodRepository neighborhoodRepository) {
        this.logging = logging;
        this.genreRepository = genreRepository;
        this.neighborhoodRepository = neighborhoodRepository;
    }

    public void toCreate(EmployeeDTO employeeDTO) {
        if (!evalMethods.isValidObject(employeeDTO)) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "The EmployeeDTO is required", AppMessages.UNKNOWN_SOURCE, employeeDTO);
            throw new IllegalArgumentException("The EmployeeDTO is required");
        }

        if (!evalMethods.isValidString(employeeDTO.getFirstName())) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "The firstname is requiered and must be a valid string", AppMessages.UNKNOWN_SOURCE, employeeDTO);
            throw new IllegalArgumentException("The firstname is requiered and must be a valid string");
        }

        if (!evalMethods.isOnlyLettersAndSpaces(employeeDTO.getFirstName())) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "The firstname must contain only letters and spaces", AppMessages.UNKNOWN_SOURCE, employeeDTO);
            throw new IllegalArgumentException("The firstname must contain only letters and spaces");
        }

        if (!evalMethods.isValidString(employeeDTO.getLastName())) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "The lastname is requiered and must be a valid string", AppMessages.UNKNOWN_SOURCE, employeeDTO);
            throw new IllegalArgumentException("The lastname is requiered and must be a valid string");
        }

        if (!evalMethods.isOnlyLettersAndSpaces(employeeDTO.getLastName())) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "The lastname must contain only letters and spaces", AppMessages.UNKNOWN_SOURCE, employeeDTO);
            throw new IllegalArgumentException("The lastname must contain only letters and spaces");
        }

        if (!evalMethods.isValidDate(employeeDTO.getBirthdate())) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "The birth date must be a valid date", AppMessages.UNKNOWN_SOURCE, employeeDTO);
            throw new IllegalArgumentException("The birth date must be a valid date");
        }

        if (!evalMethods.isMinimumAge(employeeDTO.getBirthdate(), 18)) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "The birth date must indicate the person is at least 18 years old", AppMessages.UNKNOWN_SOURCE, employeeDTO);
            throw new IllegalArgumentException("The birth date must indicate the person is at least 18 years old");
        }

        if (!evalMethods.isValidString(employeeDTO.getCurp(), 18, 18)) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "The CURP is requiered and must be a valid string with 18 characters", AppMessages.UNKNOWN_SOURCE, employeeDTO);
            throw new IllegalArgumentException("The CURP is requiered and must be a valid string with 18 characters");
        }

        if (!evalMethods.isValidString(employeeDTO.getRfc(), 10, 13)) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "The RFC is requiered and must be a valid string with 10-13 characters", AppMessages.UNKNOWN_SOURCE, employeeDTO);
            throw new IllegalArgumentException("The RFC is requiered and must be a valid string with 10-13 characters");
        }

        if (genreRepository.getGenreById(employeeDTO.getGenreId()) == null) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "The genre id is required and must be a valid id", AppMessages.UNKNOWN_SOURCE, employeeDTO);
            throw new IllegalArgumentException("The genre id is required and must be a valid id");
        }

        if (!evalMethods.isValidEmail(employeeDTO.getPersonalEmail())) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "The personal email is requiered and must be a valid email", AppMessages.UNKNOWN_SOURCE, employeeDTO);
            throw new IllegalArgumentException("The personal email is requiered and must be a valid email");
        }

        if (!evalMethods.isValidString(employeeDTO.getPersonalPhone(), 10)) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "The personal phone is requiered and must be a valid string with at least 10 characters", AppMessages.UNKNOWN_SOURCE, employeeDTO);
            throw new IllegalArgumentException("The personal phone is requiered and must be a valid string with at least 10 characters");
        }

        if (!evalMethods.isOnlyNumbers(employeeDTO.getPersonalPhone())) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "The personal phone is requiered and must be a valid string with only numbers", AppMessages.UNKNOWN_SOURCE, employeeDTO);
            throw new IllegalArgumentException("The personal phone is requiered and must be a valid string with only numbers");
        }

        if (!evalMethods.isValidString(employeeDTO.getAddress())) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "The address is requiered and must be a valid string", AppMessages.UNKNOWN_SOURCE, employeeDTO);
            throw new IllegalArgumentException("The address is requiered and must be a valid string");
        }

        if (neighborhoodRepository.getNeighborhoodById(employeeDTO.getNeighborhoodId()) == null) {
            logging.logError(AppMessages.EMPLOYEE_TO_CREATE, "The neighborhood id is required and must be a valid id", AppMessages.UNKNOWN_SOURCE, employeeDTO);
            throw new IllegalArgumentException("The neighborhood id is required and must be a valid id");
        }
    }
}
