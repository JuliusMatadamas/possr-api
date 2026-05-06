package com.possr.utils.evals;

import org.springframework.stereotype.Component;

import com.possr.constants.AppMessages;
import com.possr.dto.LoginDTO;
import com.possr.utils.Logging;

@Component
public class EvalLogin {
    private final Logging logging;
    private final EvalMethods evalMethods = new EvalMethods();

    public EvalLogin(Logging logging) {
        this.logging = logging;
    }

    public void toLogin(LoginDTO loginDTO) {
        if (!evalMethods.isValidObject(loginDTO)) {
            logging.logError(AppMessages.TO_LOGIN, "The login data is required", AppMessages.UNKNOWN_SOURCE, loginDTO);
            throw new IllegalArgumentException("The login data is required");
        }

        if (!evalMethods.isValidLong(loginDTO.getEmployeeId(), 1)) {
            logging.logError(AppMessages.TO_LOGIN, "The employee ID is required and must be 1 or greater", AppMessages.UNKNOWN_SOURCE, loginDTO);
            throw new IllegalArgumentException("The employee ID is required and must be 1 or greater");
        }

        if (!evalMethods.isValidString(loginDTO.getUsername())) {
            logging.logError(AppMessages.TO_LOGIN, "The username is required and must be a valid string", AppMessages.UNKNOWN_SOURCE, loginDTO);
            throw new IllegalArgumentException("The username is required and must be a valid string");
        }

        if (!evalMethods.isValidString(loginDTO.getPassword())) {
            logging.logError(AppMessages.TO_LOGIN, "The password is required and must be a valid string", AppMessages.UNKNOWN_SOURCE, loginDTO);
            throw new IllegalArgumentException("The password is required and must be a valid string");
        }
    }
}
