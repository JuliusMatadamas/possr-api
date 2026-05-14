package com.possr.utils.evals;

import org.springframework.stereotype.Component;

import com.possr.constants.AppMessages;
import com.possr.dto.GenreDTO;
import com.possr.utils.Logging;

@Component
public class GenreEval {
    private final Logging logging;
    private final EvalMethods evalMethods = new EvalMethods();

    public GenreEval(Logging logging) {
        this.logging = logging;
    }

    public void toCreateGenre(GenreDTO genreDTO) {
        if (!evalMethods.isValidObject(genreDTO)) {
            logging.logError(AppMessages.TO_CREATE_GENRE, "The genre data is required", AppMessages.UNKNOWN_SOURCE, genreDTO);
            throw new IllegalArgumentException("The genre data is required");
        }

        if (!evalMethods.isValidString(genreDTO.getShortName(), 1, 4)) {
            logging.logError(AppMessages.TO_CREATE_GENRE, "The genre short name is required (1-4 characters)", AppMessages.UNKNOWN_SOURCE, genreDTO);
            throw new IllegalArgumentException("The genre short name is required (1-4 characters)");
        }

        if (!evalMethods.isValidString(genreDTO.getName(), 5, 20)) {
            logging.logError(AppMessages.TO_CREATE_GENRE, "The genre name is required (5-20 characters)", AppMessages.UNKNOWN_SOURCE, genreDTO);
            throw new IllegalArgumentException("The genre name is required (5-20 characters)");
        }
    }
}
