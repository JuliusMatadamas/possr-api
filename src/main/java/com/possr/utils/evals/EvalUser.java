package com.possr.utils.evals;

import com.possr.utils.Logging;
import org.springframework.stereotype.Component;

import com.possr.dto.UserDTO;
import com.possr.repositories.possr.admin.UserRepository;
import com.possr.constants.AppMessages;

@Component
public class EvalUser {
    private final Logging logging;
    private final EvalMethods evalMethods = new EvalMethods();
    private final UserRepository userRepository;

    public EvalUser(UserRepository userRepository, Logging logging) {
        this.userRepository = userRepository;
        this.logging = logging;
    }


    public void toCreate(UserDTO userDTO) {
        if (!evalMethods.isValidObject(userDTO)) {
            logging.logError(AppMessages.USER_TO_CREATE, "The UserDTO is required", AppMessages.UNKNOWN_SOURCE, userDTO);
            throw new IllegalArgumentException("The UserDTO is required");
        }

        if(!evalMethods.isValidLong(userDTO.getEmployeeId(), 1)) {
            logging.logError(AppMessages.USER_TO_CREATE, "The employee ID is required and must be 1 or greater", AppMessages.UNKNOWN_SOURCE, userDTO);
            throw new IllegalArgumentException("The employee ID is required and must be 1 or greater");
        }

        if(!evalMethods.isValidLong(userDTO.getRoleId(), 1)) {
            logging.logError(AppMessages.USER_TO_CREATE, "The role ID is required and must be 1 or greater", AppMessages.UNKNOWN_SOURCE, userDTO);
            throw new IllegalArgumentException("The role ID is required and must be 1 or greater");
        }

        if(!evalMethods.isValidString(userDTO.getUsername(), 5, 15)) {
            logging.logError(AppMessages.USER_TO_CREATE, "The username is requiered and must be a valid string between 5 and 15 characters", AppMessages.UNKNOWN_SOURCE, userDTO);
            throw new IllegalArgumentException("The username is requiered and must be a valid string between 5 and 15 characters");
        }

        if(!evalMethods.isValidString(userDTO.getPassword(), 5, 15)) {
            logging.logError(AppMessages.USER_TO_CREATE, "The password is requiered and must be a valid string between 5 and 15 characters", AppMessages.UNKNOWN_SOURCE, userDTO);
            throw new IllegalArgumentException("The password is requiered and must be a valid string between 5 and 15 characters");
        }

        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            logging.logError(AppMessages.USER_TO_CREATE, "The username already exists", AppMessages.UNKNOWN_SOURCE, userDTO);
            throw new IllegalArgumentException("The username already exists");
        }
    }

}
