package com.example.microservices_admin_maintenance.dto;

import com.example.microservices_admin_maintenance.entity.ScheduledFareUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class DTOScheduledFareResponse {

    private final String id;

    private final String fare_to_update_id;

    private final double cost_per_min;

    private final double extended_pause_cost;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp scheduled_date;


    public DTOScheduledFareResponse (ScheduledFareUpdate sfUpdate) {
        this.id = sfUpdate.getId();
        this.fare_to_update_id = sfUpdate.getFare_to_update_id();
        this.cost_per_min = sfUpdate.getCost_per_min();
        this.extended_pause_cost = sfUpdate.getExtended_pause_cost();
        this.scheduled_date = Timestamp.valueOf(sfUpdate.getDate());
    }

}
