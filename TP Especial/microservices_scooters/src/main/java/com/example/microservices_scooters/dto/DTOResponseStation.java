package com.example.microservices_scooters.dto;

import com.example.microservices_scooters.entity.GPS;
import com.example.microservices_scooters.entity.Scooter;
import com.example.microservices_scooters.entity.Station;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;

public class DTOResponseStation {
    private long  id;
    private String name;
    private GPS location;
    private int cantMaxSkateboards;

    public DTOResponseStation(Station s ) {
        this.id = s.getId();
        this.name = s.getName();
        this.location = s.getLocation();
        this.cantMaxSkateboards = s.getCantMaxSkateboards();
    }

}
