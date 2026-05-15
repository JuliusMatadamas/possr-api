package com.possr.utils.evals;

import org.springframework.stereotype.Component;

import com.possr.constants.AppMessages;
import com.possr.dto.WorkRelationDTO;
import com.possr.utils.Logging;

@Component
public class WorkRelationEval {
    private final Logging logging;
    private final EvalMethods evalMethods = new EvalMethods();

    public WorkRelationEval(Logging logging) {
        this.logging = logging;
    }

    public void toCreateWorkRelation(WorkRelationDTO workRelationDTO) {
        validateWorkRelationObject(workRelationDTO);
        validateIds(workRelationDTO);
        validateEmail(workRelationDTO);
        validateDates(workRelationDTO);
    }

    private void validateWorkRelationObject(WorkRelationDTO workRelationDTO) {
        if (!evalMethods.isValidObject(workRelationDTO)) {
            logging.logError(AppMessages.WORK_RELATION_TO_CREATE, "The work relation data is required", AppMessages.UNKNOWN_SOURCE, workRelationDTO);
            throw new IllegalArgumentException("The work relation data is required");
        }
    }

    private void validateIds(WorkRelationDTO workRelationDTO) {
        if (!evalMethods.isValidLong(workRelationDTO.getRoleCompanyId())) {
            logging.logError(AppMessages.WORK_RELATION_TO_CREATE, "The role company ID is required", AppMessages.UNKNOWN_SOURCE, workRelationDTO);
            throw new IllegalArgumentException("The role company ID is required");
        }

        if (!evalMethods.isValidLong(workRelationDTO.getEmployeeId())) {
            logging.logError(AppMessages.WORK_RELATION_TO_CREATE, "The employee ID is required", AppMessages.UNKNOWN_SOURCE, workRelationDTO);
            throw new IllegalArgumentException("The employee ID is required");
        }
    }

    private void validateEmail(WorkRelationDTO workRelationDTO) {
        if (workRelationDTO.getEmail() == null || workRelationDTO.getEmail().trim().isEmpty()) {
            logging.logError(AppMessages.WORK_RELATION_TO_CREATE, "The email is required", AppMessages.UNKNOWN_SOURCE, workRelationDTO);
            throw new IllegalArgumentException("The email is required");
        }

        if (!evalMethods.isValidEmail(workRelationDTO.getEmail())) {
            logging.logError(AppMessages.WORK_RELATION_TO_CREATE, "The email must be a valid email", AppMessages.UNKNOWN_SOURCE, workRelationDTO);
            throw new IllegalArgumentException("The email must be a valid email");
        }
    }

    private void validateDates(WorkRelationDTO workRelationDTO) {
        if (!evalMethods.isValidString(workRelationDTO.getStartingDate())) {
            logging.logError(AppMessages.WORK_RELATION_TO_CREATE, "The starting date is required", AppMessages.UNKNOWN_SOURCE, workRelationDTO);
            throw new IllegalArgumentException("The starting date is required");
        }

        if (!evalMethods.isValidDate(workRelationDTO.getStartingDate())) {
            logging.logError(AppMessages.WORK_RELATION_TO_CREATE, "The starting date must be a valid date (YYYY-MM-DD)", AppMessages.UNKNOWN_SOURCE, workRelationDTO);
            throw new IllegalArgumentException("The starting date must be a valid date (YYYY-MM-DD)");
        }

        if (workRelationDTO.getEndingDate() != null && !evalMethods.isValidDate(workRelationDTO.getEndingDate())) {
            logging.logError(AppMessages.WORK_RELATION_TO_CREATE, "The ending date must be a valid date (YYYY-MM-DD)", AppMessages.UNKNOWN_SOURCE, workRelationDTO);
            throw new IllegalArgumentException("The ending date must be a valid date (YYYY-MM-DD)");
        }

        if (workRelationDTO.getEndingDate() != null && !evalMethods.isDateLaterThan(workRelationDTO.getEndingDate(), workRelationDTO.getStartingDate())) {
            logging.logError(AppMessages.WORK_RELATION_TO_CREATE, "The ending date must be later than the starting date", AppMessages.UNKNOWN_SOURCE, workRelationDTO);
            throw new IllegalArgumentException("The ending date must be later than the starting date");
        }
    }
}
