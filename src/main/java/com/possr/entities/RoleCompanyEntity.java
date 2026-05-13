package com.possr.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "roles_companies")
public class RoleCompanyEntity {
    @Id
    @Column(name = "role_company_id")
    long id;

    @Column(name = "company_id")
    long companyId;

    @Column(name = "role")
    String role;
}
