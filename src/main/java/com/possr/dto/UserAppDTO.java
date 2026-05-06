package com.possr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAppDTO {
    private Long id;
    private Long idCompany;
    private String nameCompany;
    private Long idWorkRole;
    private String nameWorkRole;
    private String emailWork;
    private String startingWork;
    private String endingWork;
    private String appUser;
    private String firstname;
    private String lastName;
    private String birthdate;
    private String curp;
    private String rfc;
    private Long idGenre;
    private String genre;
    private String emailPersonal;
    private String phonePersonal;
    private String personalAddress;
    private Long idNeighborhood;
    private String neighborhood;
    private Long idZipcode;
    private String zipcode;
    private Long idCounty;
    private String county;
    private Long idState;
    private String state;
    private Long idCountry;
    private String country;
    private Long idContinent;
    private String continent;
    private String token;
}
