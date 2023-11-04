package com.example.microservices_admin_maintenance.dto;

import lombok.Data;
import lombok.Getter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
public class DTORequestMaintenance {

    @Min(value = 0, message = "ID must be greater than 0")
    @NotNull(message = "ID cannot be null")
    private final Long id;
    @NotNull(message = "Cannot be null")
    private final Long scooter_id;
    @NotNull(message = "Cannot be null")
    private final Long scooter_station_id;
    private final String start_date;

    private final String end_date;

    public DTORequestMaintenance(String id, Long scooter_id, Long scooter_station_id) {
        this.id = Long.getLong(id);
        this.scooter_id = scooter_id;
        this.scooter_station_id = scooter_station_id;
        this.start_date = String.valueOf(LocalDateTime.now());
        this.end_date = null;
    }

}
