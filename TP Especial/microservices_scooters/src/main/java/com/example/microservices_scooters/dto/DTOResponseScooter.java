package com.example.microservices_scooters.dto;

import com.example.microservices_scooters.entity.GPS;
import com.example.microservices_scooters.entity.Scooter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class DTOResponseScooter {
    private Long  id;
    private String state;
    private GPS location;
    private double kmsTraveled;
    private double kmsMant;
    private Long totalUsageTime;
    private Long pausedTime;
    private Long numberOfTrips;

    public DTOResponseScooter(Scooter s ) {
        this.id = s.getId();
        this.location = s.getLocation();
        this.state = s.getState();
        this.kmsTraveled = s.getKmsTraveled();
        this.kmsMant = s.getKmsMant();
        this.totalUsageTime = s.getTotalUsageTime();
        this.pausedTime = s.getPausedTime();
        this.numberOfTrips = s.getNumberOfTrips();
    }
}
