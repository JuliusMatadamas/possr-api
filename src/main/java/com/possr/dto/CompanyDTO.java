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
public class CompanyDTO {
    @Nullable
    private String id;
    private String shortName;
    private String longName;
    private String rfc;
    private String address;
    private Long neighborhoodId;
}
