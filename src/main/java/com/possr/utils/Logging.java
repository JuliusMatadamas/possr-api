package com.possr.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Logging {
    private static final Logger logger = LoggerFactory.getLogger(Logging.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final String LEVEL_INFO = "INFO";
    private static final String LEVEL_ERROR = "ERROR";
    private static final String LEVEL_WARN = "WARN";
    private static final String LEVEL_DEBUG = "DEBUG";
    private static final String STATUS_SUCCESS = "SUCCESS";
    private static final String STATUS_ERROR = "ERROR";
    private static final String STATUS_WARNING = "WARNING";

    public void logInfo(String method, String message, String sourceIP, Object requestData) {
        logEvent(LEVEL_INFO, method, message, sourceIP, STATUS_SUCCESS, requestData);
    }

    public void logError(String method, String message, String sourceIP, Object requestData) {
        logEvent(LEVEL_ERROR, method, message, sourceIP, STATUS_ERROR, requestData);
    }

    public void logWarning(String method, String message, String sourceIP, Object requestData) {
        logEvent(LEVEL_WARN, method, message, sourceIP, STATUS_WARNING, requestData);
    }

    public void logDebug(String method, String message, String sourceIP, Object requestData) {
        logEvent(LEVEL_DEBUG, method, message, sourceIP, LEVEL_DEBUG, requestData);
    }

    private void logEvent(String level, String method, String message, String sourceIP, String status, Object requestData) {
        try {
            LogEvent logEvent = LogEvent.builder()
                    .timestamp(LocalDateTime.now().format(dateTimeFormatter))
                    .level(level)
                    .logType("TRANSACTION")
                    .sourceIP(sourceIP != null ? sourceIP : "UNKNOWN")
                    .status(status)
                    .message(message)
                    .logOrigin("INTERNAL")
                    .transactionId(UUID.generate())
                    .method(method)
                    .requestData(requestData)
                    .build();

            String logJson = objectMapper.writeValueAsString(logEvent);

            switch (level) {
                case LEVEL_ERROR:
                    logger.error(logJson);
                    break;
                case LEVEL_WARN:
                    logger.warn(logJson);
                    break;
                case LEVEL_DEBUG:
                    logger.debug(logJson);
                    break;
                default:
                    logger.info(logJson);
            }
        } catch (Exception e) {
            logger.error("Error al serializar el log: {}", e.getMessage());
        }
    }
}
