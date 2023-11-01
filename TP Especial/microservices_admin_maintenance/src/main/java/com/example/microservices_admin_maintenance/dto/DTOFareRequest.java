package com.example.microservices_admin_maintenance.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DTOFareRequest {

    private String name;

    private double cost;

    private double extended_pause_cost;

    public DTOFareRequest(String name, double cost, double extendedPauseCost) {
        this.name = name;
        this.cost = cost;
        extended_pause_cost = extendedPauseCost;
    }

}
