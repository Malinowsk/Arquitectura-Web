package com.example.microservices_admin_maintenance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
public class DTOScheduledFareRequest {

    @NotNull
    private String fare_to_update_id;

    @NotNull
    private double cost_per_min;

    @NotNull
    private double extended_pause_cost;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp scheduled_date;

    public DTOScheduledFareRequest(String fare_to_update_id, double cost_per_min, double extended_pause_cost, Timestamp scheduled_date) {
        this.fare_to_update_id = fare_to_update_id;
        this.cost_per_min = cost_per_min;
        this.extended_pause_cost = extended_pause_cost;
        this.scheduled_date = scheduled_date;
    }
}
