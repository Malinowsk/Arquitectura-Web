package com.example.microservices_scooters.dto;

import lombok.Getter;

@Getter
public class DTORespondeStatusQualityScooter {

    private String status ;
    private int quatity;

    public DTORespondeStatusQualityScooter(String status, int quatity) {
        this.status = status;
        this.quatity = quatity;
    }
}
