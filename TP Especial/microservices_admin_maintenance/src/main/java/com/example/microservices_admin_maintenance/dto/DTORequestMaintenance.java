package com.example.microservices_admin_maintenance.dto;

import lombok.Getter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

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
    @NotNull(message = "Cannot be null")
    private final Timestamp start_date;

    private final Timestamp end_date;

    public DTORequestMaintenance(Long id, Long scooter_id, Long scooter_station_id) {
        this.id = id;
        this.scooter_id = scooter_id;
        this.scooter_station_id = scooter_station_id;
        this.start_date = Timestamp.valueOf(LocalDateTime.now());
        this.end_date = null;
    }

}
