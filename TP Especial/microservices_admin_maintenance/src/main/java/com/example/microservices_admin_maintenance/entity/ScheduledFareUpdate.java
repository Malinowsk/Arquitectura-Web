package com.example.microservices_admin_maintenance.entity;

import com.example.microservices_admin_maintenance.dto.DTOScheduledFareRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ScheduledFareUpdate")
@Data
@NoArgsConstructor
public class ScheduledFareUpdate {

    @Id
    private String id;

    private String fare_to_update_id;

    //@Column
    private double cost_per_min;

    //@Column
    private double extended_pause_cost;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String date;

    public ScheduledFareUpdate(String fare_to_update_id, double cost_per_min, double extended_pause_cost, String date) {
        this.fare_to_update_id = fare_to_update_id;
        this.cost_per_min = cost_per_min;
        this.extended_pause_cost = extended_pause_cost;
        this.date = date;
    }

    public ScheduledFareUpdate(DTOScheduledFareRequest sfDTO) {
        this.fare_to_update_id = sfDTO.getFare_to_update_id();
        this.cost_per_min = sfDTO.getCost_per_min();
        this.extended_pause_cost = sfDTO.getExtended_pause_cost();
        this.date = sfDTO.getScheduled_date().toString();

    }

}
