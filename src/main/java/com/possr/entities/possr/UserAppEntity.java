package com.possr.entities.possr;

import java.time.LocalDate;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Immutable
@Subselect("""
    SELECT
        u.id AS user_id,
        u.username AS username,
        c.id AS id_company,
        c.name AS name_company,
        u.role_id AS id_role,
        ar.name AS name_role,
        wrel.work_role_id AS id_work_role,
        wro.name AS name_work_role,
        wrel.work_email AS email_work,
        wrel.starting_date AS starting_work,
        wrel.ending_date AS ending_work,
        wrel.app_user AS app_user,
        e.firstname AS firstname,
        e.lastname AS lastname,
        e.birthdate AS birthdate,
        e.curp AS curp,
        e.rfc AS rfc,
        g.id AS id_genre,
        g.name AS genre,
        e.personal_email AS email_personal,
        e.personal_phone AS phone_personal,
        e.address AS personal_address,
        n.id AS id_neighborhood,
        n.name AS neighborhood,
        z.id AS id_zipcode,
        z.zipcode AS zipcode,
        co.id AS id_county,
        co.name AS county,
        s.id AS id_state,
        s.name AS state,
        ctr.id AS id_country,
        ctr.name AS country,
        con.id AS id_continent,
        con.name AS continent,
        u.password AS password
    FROM users u
    INNER JOIN app_roles ar ON u.role_id = ar.id
    INNER JOIN employees e ON u.employee_id = e.id
    INNER JOIN genres g ON e.genre_id = g.id
    LEFT JOIN neighborhoods n ON e.neighborhood_id = n.id
    LEFT JOIN zipcodes z ON n.zipcode_id = z.id
    LEFT JOIN counties co ON z.county_id = co.id
    LEFT JOIN states s ON co.state_id = s.id
    LEFT JOIN countries ctr ON s.country_id = ctr.id
    LEFT JOIN continents con ON ctr.continent_id = con.id
    INNER JOIN (
        SELECT employee_id, work_role_id, work_email, starting_date, ending_date, app_user
        FROM work_relations
        WHERE deleted_at IS NULL
        AND app_user = 1
        AND starting_date <= CURDATE()
        AND (ending_date IS NULL OR ending_date >= CURDATE())
        ORDER BY COALESCE(updated_at, created_at) DESC
        LIMIT 1
    ) wrel ON e.id = wrel.employee_id
    INNER JOIN work_roles wro ON wrel.work_role_id = wro.id
    INNER JOIN companies c ON wro.company_id = c.id
    """)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAppEntity {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "id_company")
    private Long idCompany;

    @Column(name = "name_company")
    private String nameCompany;

    @Column(name = "id_role")
    private Long idRole;

    @Column(name = "name_role")
    private String nameRole;

    @Column(name = "id_work_role")
    private Long idWorkRole;

    @Column(name = "name_work_role")
    private String nameWorkRole;

    @Column(name = "email_work")
    private String emailWork;

    @Column(name = "starting_work")
    private LocalDate startingWork;

    @Column(name = "ending_work")
    private LocalDate endingWork;

    @Column(name = "app_user")
    private Boolean appUser;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "curp")
    private String curp;

    @Column(name = "rfc")
    private String rfc;

    @Column(name = "id_genre")
    private Long idGenre;

    @Column(name = "genre")
    private String genre;

    @Column(name = "email_personal")
    private String emailPersonal;

    @Column(name = "phone_personal")
    private String phonePersonal;

    @Column(name = "personal_address")
    private String personalAddress;

    @Column(name = "id_neighborhood")
    private Long idNeighborhood;

    @Column(name = "neighborhood")
    private String neighborhood;

    @Column(name = "id_zipcode")
    private Long idZipcode;

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "id_county")
    private Long idCounty;

    @Column(name = "county")
    private String county;

    @Column(name = "id_state")
    private Long idState;

    @Column(name = "state")
    private String state;

    @Column(name = "id_country")
    private Long idCountry;

    @Column(name = "country")
    private String country;

    @Column(name = "id_continent")
    private Long idContinent;

    @Column(name = "continent")
    private String continent;

    @Column(name = "password")
    private String password;
}
