package com.example.microservices_scooters.dto;

import lombok.Getter;

@Getter
public class DTORespondeStatusQualityScooter {

    private String status ;
    private long quatity;

    public DTORespondeStatusQualityScooter(String status, long quatity) {
        this.status = status;
        this.quatity = quatity;
    }
}
