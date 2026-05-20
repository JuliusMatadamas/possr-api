package com.possr.dto;

import java.time.LocalDateTime;

import io.github.resilience4j.core.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    @Nullable
    private Long id;
    private String firstname;
    private String lastname;
    @Nullable
    private String birthdate;
    private Long genreId;
    @Nullable
    private String curp;
    @Nullable
    private String nss;
    @Nullable
    private String rfc;
    @Nullable
    private String personalPhone;
    @Nullable
    private String personalEmail;
    @Nullable
    private String address;
    private Long neighborhoodId;
    @Nullable
    private LocalDateTime createdAt;
    @Nullable
    private LocalDateTime deletedAt;
    @Nullable
    private LocalDateTime updatedAt;
}
