package com.possr.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogEvent {
    private String timestamp;
    private String level;
    private String logType;
    private String sourceIP;
    private String status;
    private String message;
    private String logOrigin;
    private String transactionId;
    private String method;
    private Object requestData;
}
