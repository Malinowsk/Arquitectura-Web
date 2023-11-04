package com.example.microservices_admin_maintenance.dto;

import com.example.microservices_admin_maintenance.entity.Fare;
import lombok.Getter;

@Getter
public class DTOFareResponse {

    private final Long id;

    private final String name;

    private final double cost_per_min;

    private final double extended_pause_cost;

    public DTOFareResponse(Long id, String name, double cost, double extended_pause_cost) {
        this.id = id;
        this.name = name;
        this.cost_per_min = cost;
        this.extended_pause_cost = extended_pause_cost;
    }

    public DTOFareResponse(Fare fare) {
        this.id = Long.valueOf(fare.getId());
        this.name = fare.getName();
        this.cost_per_min = fare.getCost_per_min();
        this.extended_pause_cost = fare.getExtended_pause_cost();
    }

}
