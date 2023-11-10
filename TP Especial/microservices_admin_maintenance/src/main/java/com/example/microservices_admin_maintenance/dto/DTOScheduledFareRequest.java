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
    private double cost_per_min;

    @NotNull
    private double extended_pause_cost;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp scheduled_date;

    public DTOScheduledFareRequest(double costPerMin, double extendedPauseCost, Timestamp scheduledDate) {
        cost_per_min = costPerMin;
        extended_pause_cost = extendedPauseCost;
        scheduled_date = scheduledDate;
    }
}
