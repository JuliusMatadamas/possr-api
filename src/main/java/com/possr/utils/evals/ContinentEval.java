package com.possr.utils.evals;

import org.springframework.stereotype.Component;

import com.possr.constants.AppMessages;
import com.possr.dto.ContinentDTO;
import com.possr.utils.Logging;

@Component
public class ContinentEval {
	private final Logging logging;
	private final EvalMethods evalMethods = new EvalMethods();

	public ContinentEval(Logging logging) {
		this.logging = logging;
	}

	public void toCreateContinent (ContinentDTO continentDTO){
		validateContinentObject(continentDTO);
		validateContinentName(continentDTO);
	}

	private void validateContinentObject (ContinentDTO continentDTO){
		if (!evalMethods.isValidObject(continentDTO)) {
			logging.logError(AppMessages.CONTINENT_TO_CREATE, "The continent data is required", AppMessages.UNKNOWN_SOURCE, continentDTO);
            throw new IllegalArgumentException("The continent data is required");				
		}	
	}

	private void validateContinentName (ContinentDTO continentDTO){
		if (!evalMethods.isValidString(continentDTO.getName(), 2, 50)) {
			logging.logError(AppMessages.CONTINENT_TO_CREATE, "The continent name is required (2-50 characters)", AppMessages.UNKNOWN_SOURCE, continentDTO);
            throw new IllegalArgumentException("The continent name is required (2-50 characters)");				
		}	
	}

}
