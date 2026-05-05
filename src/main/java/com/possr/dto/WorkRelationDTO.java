package com.possr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkRelationDTO {
    private Long employeeId;
    private Long workRoleId;
    private String workEmail;
    private Boolean appUser;
    private String startingDate;
    private String endingDate;
}
