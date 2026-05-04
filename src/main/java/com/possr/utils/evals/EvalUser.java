package com.possr.utils.evals;

import org.springframework.stereotype.Component;

import com.possr.dto.UserDTO;
import com.possr.repositories.possr.admin.UserRepository;

@Component
public class EvalUser {
    private final EvalMethods evalMethods = new EvalMethods();
    private final UserRepository userRepository;

    public EvalUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void toCreate(UserDTO userDTO) {
        if (!evalMethods.isValidObject(userDTO)) {
            throw new IllegalArgumentException("The UserDTO is required");
        }

        if(!evalMethods.isValidLong(userDTO.getEmployeeId(), 1)) {
            throw new IllegalArgumentException("The employee ID is required and must be 1 or greater");
        }

        if(!evalMethods.isValidLong(userDTO.getRoleId(), 1)) {
            throw new IllegalArgumentException("The role ID is required and must be 1 or greater");
        }

        if(!evalMethods.isValidString(userDTO.getUsername(), 5, 15)) {
            throw new IllegalArgumentException("The username is requiered and must be a valid string between 5 and 15 characters");
        }

        if(!evalMethods.isValidString(userDTO.getPassword(), 5, 15)) {
            throw new IllegalArgumentException("The password is requiered and must be a valid string between 5 and 15 characters");
        }

        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            throw new IllegalArgumentException("The username already exists");
        }
    }

}
