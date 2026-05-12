package com.possr.utils.evals;

import org.springframework.stereotype.Component;

import com.possr.constants.AppMessages;
import com.possr.dto.LoginDTO;
import com.possr.utils.Logging;

@Component
public class LoginEval {
    private final Logging logging;
    private final EvalMethods evalMethods = new EvalMethods();

    public LoginEval(Logging logging) {
        this.logging = logging;
    }

    public void toLogin(LoginDTO loginDTO) {
        if (!evalMethods.isValidObject(loginDTO)) {
            logging.logError(AppMessages.TO_LOGIN, "The login data is required", AppMessages.UNKNOWN_SOURCE, loginDTO);
            throw new IllegalArgumentException("The login data is required");
        }

        if(!evalMethods.isValidEmail(loginDTO.getEmail())) {
            logging.logError(AppMessages.TO_LOGIN, "The email is required and must be a valid email", AppMessages.UNKNOWN_SOURCE, loginDTO);
            throw new IllegalArgumentException("The email is required and must be a valid email");
        }

        if (!evalMethods.isValidString(loginDTO.getPassword())) {
            logging.logError(AppMessages.TO_LOGIN, "The password is required and must be a valid string", AppMessages.UNKNOWN_SOURCE, loginDTO);
            throw new IllegalArgumentException("The password is required and must be a valid string");
        }
    }
}
