package com.example.microservices_admin_maintenance.entity;

import com.example.microservices_admin_maintenance.dto.DTORequestMaintenance;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;
import java.sql.Timestamp;

//@Entity
@Document(collation = "Maintenance")
@Data  // genera automáticamente los métodos getter, setter, toString(), equals(), y hashCode()
@NoArgsConstructor // genera automáticamente el contructor vacio
public class Maintenance {

    @Id
    private String id;

    //@Column (nullable = false)
    private String start_date;

    //@Column
    private String end_date;

    //@Column (nullable = false)
    private String scooter_id;

    //@Column (nullable = false)
    private String scooter_station_id;

    public Maintenance(Timestamp start_date, Timestamp end_date, Long scooter_id, Long scooter_station_id) {
        this.start_date = String.valueOf(start_date);
        this.end_date = String.valueOf(end_date);
        this.scooter_id = String.valueOf(scooter_id);
        this.scooter_station_id = String.valueOf(scooter_station_id);
    }

    public Maintenance(DTORequestMaintenance request) {
        this.id = String.valueOf(request.getId());
        this.start_date = request.getStart_date();
        this.end_date = request.getEnd_date();
        this.scooter_id = String.valueOf(request.getScooter_id());
        this.scooter_station_id = String.valueOf(request.getScooter_station_id());
    }

}
