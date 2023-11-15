package com.example.microservices_admin_maintenance.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DTOFareRequest {

    private String name;

    private double cost_per_min = -1;

    private double extended_pause_cost = -1;

    public DTOFareRequest(String name, double cost, double extendedPauseCost) {
        this.name = name;
        this.cost_per_min = cost;
        extended_pause_cost = extendedPauseCost;
    }

}
