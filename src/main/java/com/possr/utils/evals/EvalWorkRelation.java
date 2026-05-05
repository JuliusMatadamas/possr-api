package com.possr.utils.evals;

import org.springframework.stereotype.Component;

import com.possr.constants.AppMessages;
import com.possr.dto.WorkRelationDTO;
import com.possr.utils.Logging;

@Component
public class EvalWorkRelation {
    private final Logging logging;
    private final EvalMethods evalMethods = new EvalMethods();

    public EvalWorkRelation(Logging logging) {
        this.logging = logging;
    }

    public void toCreate(WorkRelationDTO workRelationDTO) {
        if (!evalMethods.isValidLong(workRelationDTO.getEmployeeId(), 1)) {
            logging.logError(AppMessages.WORK_RELATION_TO_CREATE, "The employee ID is required and must be 1 or greater", AppMessages.UNKNOWN_SOURCE, workRelationDTO);
            throw new IllegalArgumentException("The employee ID is required and must be 1 or greater");
        }

        if (!evalMethods.isValidLong(workRelationDTO.getWorkRoleId())) {
            logging.logError(AppMessages.WORK_RELATION_TO_CREATE, "The work role ID is required and must be 1 or greater", AppMessages.UNKNOWN_SOURCE, workRelationDTO);
            throw new IllegalArgumentException("The work role ID is required and must be 1 or greater");
        }

        if (!evalMethods.isValidEmail(workRelationDTO.getWorkEmail())) {
            logging.logError(AppMessages.WORK_RELATION_TO_CREATE, "The work email is required and must be valid", AppMessages.UNKNOWN_SOURCE, workRelationDTO);
            throw new IllegalArgumentException("The work email is required and must be valid");
        }

        if (!evalMethods.isValidBoolean(workRelationDTO.getAppUser())) {
            logging.logError(AppMessages.WORK_RELATION_TO_CREATE, "The app user must be 0 or 1", AppMessages.UNKNOWN_SOURCE, workRelationDTO);
            throw new IllegalArgumentException("The app user must be 0 or 1");
        }

        if (!evalMethods.isValidDate(workRelationDTO.getStartingDate())) {
            logging.logError(AppMessages.WORK_RELATION_TO_CREATE, "The starting date is required and must be valid", AppMessages.UNKNOWN_SOURCE, workRelationDTO);
            throw new IllegalArgumentException("The starting date is required and must be valid");
        }

        if (workRelationDTO.getEndingDate() != null) {
            if (!evalMethods.isValidDate(workRelationDTO.getEndingDate())) {
                logging.logError(AppMessages.WORK_RELATION_TO_CREATE, "The ending date must be valid", AppMessages.UNKNOWN_SOURCE, workRelationDTO);
                throw new IllegalArgumentException("The ending date must be valid");
            }

            if (!evalMethods.areDatesDifferent(workRelationDTO.getStartingDate(), workRelationDTO.getEndingDate())) {
                logging.logError(AppMessages.WORK_RELATION_TO_CREATE, "The ending date must be on or after the starting date", AppMessages.UNKNOWN_SOURCE, workRelationDTO);
                throw new IllegalArgumentException("The ending date must be on or after the starting date");
            }

            if (!evalMethods.isDateLaterThan(workRelationDTO.getEndingDate(), workRelationDTO.getStartingDate())) {
                logging.logError(AppMessages.WORK_RELATION_TO_CREATE, "The ending date must be later than the starting date", AppMessages.UNKNOWN_SOURCE, workRelationDTO);
                throw new IllegalArgumentException("The ending date must be later than the starting date");
            }

            if (evalMethods.isDateOnOrBeforeCurrentDate(workRelationDTO.getEndingDate()) && Boolean.TRUE.equals(workRelationDTO.getAppUser())) {
                logging.logError(AppMessages.WORK_RELATION_TO_CREATE, "Access to the app connot be granted because the ending date has passed", AppMessages.UNKNOWN_SOURCE, workRelationDTO);
                throw new IllegalArgumentException("Access to the app connot be granted because the ending date has passed");
            }
        }
    }
}
