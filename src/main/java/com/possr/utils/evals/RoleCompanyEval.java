package com.possr.utils.evals;

import org.springframework.stereotype.Component;

import com.possr.constants.AppMessages;
import com.possr.dto.RoleCompanyDTO;
import com.possr.utils.Logging;

@Component
public class RoleCompanyEval {
    private final Logging logging;
    private final EvalMethods evalMethods = new EvalMethods();

    public RoleCompanyEval(Logging logging) {
        this.logging = logging;
    }

    public void toCreateRoleCompany(RoleCompanyDTO roleCompanyDTO) {
        if (!evalMethods.isValidObject(roleCompanyDTO)) {
            logging.logError(AppMessages.TO_CREATE_ROLE_COMPANY, "The role company data is required", AppMessages.UNKNOWN_SOURCE, roleCompanyDTO);
            throw new IllegalArgumentException("The role company data is required");
        }

        if (!evalMethods.isValidLong(roleCompanyDTO.getCompanyId())) {
            logging.logError(AppMessages.TO_CREATE_ROLE_COMPANY, "The company ID is required", AppMessages.UNKNOWN_SOURCE, roleCompanyDTO);
            throw new IllegalArgumentException("The company ID is required");
        }

        if (!evalMethods.isValidString(roleCompanyDTO.getRole())) {
            logging.logError(AppMessages.TO_CREATE_ROLE_COMPANY, "The role is required", AppMessages.UNKNOWN_SOURCE, roleCompanyDTO);
            throw new IllegalArgumentException("The role is required");
        }
    }
}
