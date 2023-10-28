package com.example.microservices_admin_maintenance.dto;

import com.example.microservices_admin_maintenance.entity.Maintenance;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class DTOResponseMaintenance {

    private final Long id;

    private final Long scooter_id;

    private final Long scooter_station_id;

    private final Timestamp start_date;

    private Timestamp end_date;


    public DTOResponseMaintenance(Long id, Long scooter_id, Long scooter_station_id, Timestamp start_date, Timestamp end_date) {
        this.id = id;
        this.scooter_id = scooter_id;
        this.scooter_station_id = scooter_station_id;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public DTOResponseMaintenance(Maintenance result) {
        this.id = result.getId();
        this.scooter_id = result.getScooter_id();
        this.scooter_station_id = result.getScooter_station_id();
        this.start_date = result.getStart_date();
        this.end_date = result.getEnd_date();
    }

    public void setEnd_date(Timestamp end_date) {
        this.end_date = end_date;
    }
}
