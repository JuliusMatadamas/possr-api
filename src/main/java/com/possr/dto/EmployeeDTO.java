package com.possr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String birthdate;
    private String curp;
    private String rfc;
    private Long genreId;
    private String personalEmail;
    private String personalPhone;
    private String address;
    private Long neighborhoodId;
}
