package com.camper.camport.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class CampLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long campLocationId;

    private String campLocationName;

    private String campLocationAddress;

}
