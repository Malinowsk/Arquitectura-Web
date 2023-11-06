package com.example.microservices_scooters.dto;

import lombok.Getter;

@Getter
public class DTORespondeStatusQualityScooter {

    private long quatity_in_use;
    private long quatity_maintenance;

    public DTORespondeStatusQualityScooter(long quatity_in_use, long quatity_maintenance) {
        this.quatity_in_use = quatity_in_use;
        this.quatity_maintenance = quatity_maintenance;
    }
}
