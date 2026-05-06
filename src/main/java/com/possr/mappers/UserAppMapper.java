package com.possr.mappers;

import org.springframework.stereotype.Component;

import com.possr.dto.UserAppDTO;
import com.possr.entities.possr.UserAppEntity;

@Component
public class UserAppMapper {

    public UserAppDTO toDTO(UserAppEntity entity, String token) {
        return UserAppDTO.builder()
                .id(entity.getUserId())
                .idCompany(entity.getIdCompany())
                .nameCompany(entity.getNameCompany())
                .idWorkRole(entity.getIdWorkRole())
                .nameWorkRole(entity.getNameWorkRole())
                .emailWork(entity.getEmailWork())
                .startingWork(entity.getStartingWork() != null ? entity.getStartingWork().toString() : null)
                .endingWork(entity.getEndingWork() != null ? entity.getEndingWork().toString() : null)
                .appUser(entity.getAppUser() != null ? entity.getAppUser().toString() : null)
                .firstname(entity.getFirstname())
                .lastName(entity.getLastName())
                .birthdate(entity.getBirthdate() != null ? entity.getBirthdate().toString() : null)
                .curp(entity.getCurp())
                .rfc(entity.getRfc())
                .idGenre(entity.getIdGenre())
                .genre(entity.getGenre())
                .emailPersonal(entity.getEmailPersonal())
                .phonePersonal(entity.getPhonePersonal())
                .personalAddress(entity.getPersonalAddress())
                .idNeighborhood(entity.getIdNeighborhood())
                .neighborhood(entity.getNeighborhood())
                .idZipcode(entity.getIdZipcode())
                .zipcode(entity.getZipcode())
                .idCounty(entity.getIdCounty())
                .county(entity.getCounty())
                .idState(entity.getIdState())
                .state(entity.getState())
                .idCountry(entity.getIdCountry())
                .country(entity.getCountry())
                .idContinent(entity.getIdContinent())
                .continent(entity.getContinent())
                .token(token)
                .build();
    }

    public UserAppEntity toEntity(UserAppDTO dto) {
        return UserAppEntity.builder()
                .userId(dto.getId())
                .idCompany(dto.getIdCompany())
                .nameCompany(dto.getNameCompany())
                .idWorkRole(dto.getIdWorkRole())
                .nameWorkRole(dto.getNameWorkRole())
                .emailWork(dto.getEmailWork())
                .startingWork(dto.getStartingWork() != null ? java.time.LocalDate.parse(dto.getStartingWork()) : null)
                .endingWork(dto.getEndingWork() != null ? java.time.LocalDate.parse(dto.getEndingWork()) : null)
                .appUser(dto.getAppUser() != null ? Boolean.parseBoolean(dto.getAppUser()) : null)
                .firstname(dto.getFirstname())
                .lastName(dto.getLastName())
                .birthdate(dto.getBirthdate() != null ? java.time.LocalDate.parse(dto.getBirthdate()) : null)
                .curp(dto.getCurp())
                .rfc(dto.getRfc())
                .idGenre(dto.getIdGenre())
                .genre(dto.getGenre())
                .emailPersonal(dto.getEmailPersonal())
                .phonePersonal(dto.getPhonePersonal())
                .personalAddress(dto.getPersonalAddress())
                .idNeighborhood(dto.getIdNeighborhood())
                .neighborhood(dto.getNeighborhood())
                .idZipcode(dto.getIdZipcode())
                .zipcode(dto.getZipcode())
                .idCounty(dto.getIdCounty())
                .county(dto.getCounty())
                .idState(dto.getIdState())
                .state(dto.getState())
                .idCountry(dto.getIdCountry())
                .country(dto.getCountry())
                .idContinent(dto.getIdContinent())
                .continent(dto.getContinent())
                .build();
    }
}
