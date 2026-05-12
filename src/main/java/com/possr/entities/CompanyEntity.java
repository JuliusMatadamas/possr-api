package com.possr.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "companies")
public class CompanyEntity {
    @Id
    @Column(name = "company_id")
    long id;

    @Column(name = "short_name")
    String shortName;

    @Column(name = "long_name")
    String longName;

    @Column(name = "rfc")
    String rfc;

    @Column(name = "address")
    String address;

    @Column(name = "neighborhood_id")
    long neighborhoodId;
}
