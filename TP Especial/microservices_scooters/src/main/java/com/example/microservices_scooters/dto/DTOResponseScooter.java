package com.example.microservices_scooters.dto;

import com.example.microservices_scooters.entity.GPS;
import com.example.microservices_scooters.entity.Scooter;
import lombok.Getter;

@Getter
public class DTOResponseScooter {
    private Long  id;
    private String state;
    private GPS location;
    private double kmsTraveled;
    private double kmsMant;
    private long totalUsageTime;
    private long pausedTime;
    private int numberOfTrips;
    private String model;

    public DTOResponseScooter(Scooter s ) {
        this.id = s.getId();
        this.location = s.getLocation();
        this.state = s.getState();
        this.kmsTraveled = s.getKmsTraveled();
        this.kmsMant = s.getKmsMant();
        this.totalUsageTime = s.getTotalUsageTime();
        this.pausedTime = s.getPausedTime();
        this.numberOfTrips = s.getNumberOfTrips();
        this.model = s.getModel();
    }
}
