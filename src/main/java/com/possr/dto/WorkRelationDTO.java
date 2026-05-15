package com.possr.dto;

import io.github.resilience4j.core.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkRelationDTO {
    @Nullable
    private Long id;
    private Long roleCompanyId;
    private Long employeeId;
    private String email;
    @Nullable
    private Boolean userApp;
    private String startingDate;
    @Nullable
    private String endingDate;
}
