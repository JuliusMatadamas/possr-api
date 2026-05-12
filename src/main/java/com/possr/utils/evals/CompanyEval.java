package com.possr.utils.evals;

import org.springframework.stereotype.Component;

import com.possr.constants.AppMessages;
import com.possr.dto.CompanyDTO;
import com.possr.utils.Logging;

@Component
public class CompanyEval {
    private final Logging logging;
    private final EvalMethods evalMethods = new EvalMethods();

    public CompanyEval(Logging logging) {
        this.logging = logging;
    }

    public void toCreateCompany(CompanyDTO companyDTO) {
        if (!evalMethods.isValidObject(companyDTO)) {
            logging.logError(AppMessages.TO_CREATE_COMPANY, "The company data is required", AppMessages.UNKNOWN_SOURCE, companyDTO);
            throw new IllegalArgumentException("The company data is required");
        }

        if (!evalMethods.isValidString(companyDTO.getShortName(), 3, 4)) {
            logging.logError(AppMessages.TO_CREATE_COMPANY, "The company short name is required (3-4 characters)", AppMessages.UNKNOWN_SOURCE, companyDTO);
            throw new IllegalArgumentException("The company short name is required (3-4 characters)");
        }

        if (!evalMethods.isValidString(companyDTO.getLongName(), 5)) {
            logging.logError(AppMessages.TO_CREATE_COMPANY, "The company long name is required (min 5 characters)", AppMessages.UNKNOWN_SOURCE, companyDTO);
            throw new IllegalArgumentException("The company long name is required (min 5 characters)");
        }

        if (!evalMethods.isValidString(companyDTO.getRfc(), 10, 15)) {
            logging.logError(AppMessages.TO_CREATE_COMPANY, "The company RFC is required (10-15 characters)", AppMessages.UNKNOWN_SOURCE, companyDTO);
            throw new IllegalArgumentException("The company RFC is required (10-15 characters)");
        }

        if (!evalMethods.isValidString(companyDTO.getAddress())) {
            logging.logError(AppMessages.TO_CREATE_COMPANY, "The company address is required", AppMessages.UNKNOWN_SOURCE, companyDTO);
            throw new IllegalArgumentException("The company address is required");
        }

        if (!evalMethods.isValidLong(companyDTO.getNeighborhoodId())) {
            logging.logError(AppMessages.TO_CREATE_COMPANY, "The company neighborhood ID is required", AppMessages.UNKNOWN_SOURCE, companyDTO);
            throw new IllegalArgumentException("The company neighborhood ID is required");
        }
    }
}
