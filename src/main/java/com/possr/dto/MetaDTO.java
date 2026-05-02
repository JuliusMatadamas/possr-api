package com.possr.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.possr.utils.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_DEFAULT)
public class MetaDTO {
    private boolean rollback;
    private final String timestamp = LocalDateTime.now().toString();
    private final String transactionId = UUID.generate();
    private int retryAfter;
    private int statusCode;
    private Object errorDetails;
    private Object upstreamError;
    private String devMessage;
    private String message;
    private String status;
}
