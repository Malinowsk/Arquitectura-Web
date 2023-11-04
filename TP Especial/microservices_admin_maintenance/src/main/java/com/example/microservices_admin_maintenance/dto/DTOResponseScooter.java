package com.example.microservices_admin_maintenance.dto;

import lombok.Getter;

@Getter
public class DTOResponseScooter {
    private Long  id;
    private String state;
    private double kmsTraveled;
    private double kmsMant;
    private long totalUsageTime;
    private long pausedTime;
    private int numberOfTrips;
    private String model;

    public DTOResponseScooter(Long id, String state, double kmsTraveled, double kmsMant, long totalUsageTime, long pausedTime, int numberOfTrips, String model) {
        this.id = id;
        this.state = state;
        this.kmsTraveled = kmsTraveled;
        this.kmsMant = kmsMant;
        this.totalUsageTime = totalUsageTime;
        this.pausedTime = pausedTime;
        this.numberOfTrips = numberOfTrips;
        this.model = model;
    }
}
