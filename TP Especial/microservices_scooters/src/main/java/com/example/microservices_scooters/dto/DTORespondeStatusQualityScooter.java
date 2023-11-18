package com.example.microservices_scooters.dto;

import lombok.Getter;

@Getter
public class DTORespondeStatusQualityScooter {

    private final long quantity_in_use;
    private final long quantity_maintenance;

    public DTORespondeStatusQualityScooter(long quantity_in_use, long quantity_maintenance) {
        this.quantity_in_use = quantity_in_use;
        this.quantity_maintenance = quantity_maintenance;
    }
}
