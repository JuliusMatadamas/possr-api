package com.possr.utils.evals;

import com.possr.utils.Logging;
import org.springframework.stereotype.Component;

import com.possr.dto.UserToCreateDTO;
import com.possr.repositories.possr.admin.UserRepository;
import com.possr.constants.AppMessages;

@Component
public class EvalUser {
    private final Logging logging;
    private final EvalMethods evalMethods = new EvalMethods();
    private final UserRepository userRepository;

    public EvalUser(
            UserRepository userRepository,
            Logging logging) {
        this.userRepository = userRepository;
        this.logging = logging;
    }


    public void toCreate(UserToCreateDTO userToCreateDTO) {
        if (!evalMethods.isValidObject(userToCreateDTO)) {
            logging.logError(AppMessages.USER_TO_CREATE, "The UserToCreateDTO is required", AppMessages.UNKNOWN_SOURCE, userToCreateDTO);
            throw new IllegalArgumentException("The UserToCreateDTO is required");
        }

        if(!evalMethods.isValidLong(userToCreateDTO.getEmployeeId(), 1)) {
            logging.logError(AppMessages.USER_TO_CREATE, "The employee ID is required and must be 1 or greater", AppMessages.UNKNOWN_SOURCE, userToCreateDTO);
            throw new IllegalArgumentException("The employee ID is required and must be 1 or greater");
        }

        if(!evalMethods.isValidLong(userToCreateDTO.getRoleId(), 1)) {
            logging.logError(AppMessages.USER_TO_CREATE, "The role ID is required and must be 1 or greater", AppMessages.UNKNOWN_SOURCE, userToCreateDTO);
            throw new IllegalArgumentException("The role ID is required and must be 1 or greater");
        }

        if(!evalMethods.isValidString(userToCreateDTO.getUsername(), 5, 15)) {
            logging.logError(AppMessages.USER_TO_CREATE, "The username is requiered and must be a valid string between 5 and 15 characters", AppMessages.UNKNOWN_SOURCE, userToCreateDTO);
            throw new IllegalArgumentException("The username is requiered and must be a valid string between 5 and 15 characters");
        }

        if(!evalMethods.isValidString(userToCreateDTO.getPassword(), 5, 15)) {
            logging.logError(AppMessages.USER_TO_CREATE, "The password is requiered and must be a valid string between 5 and 15 characters", AppMessages.UNKNOWN_SOURCE, userToCreateDTO);
            throw new IllegalArgumentException("The password is requiered and must be a valid string between 5 and 15 characters");
        }

        if (userRepository.findByUsername(userToCreateDTO.getUsername()) != null) {
            logging.logError(AppMessages.USER_TO_CREATE, "The username already exists", AppMessages.UNKNOWN_SOURCE, userToCreateDTO);
            throw new IllegalArgumentException("The username already exists");
        }
    }

}
