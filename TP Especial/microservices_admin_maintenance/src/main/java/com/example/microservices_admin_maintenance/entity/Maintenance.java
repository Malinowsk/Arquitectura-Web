package com.example.microservices_admin_maintenance.entity;

import com.example.microservices_admin_maintenance.dto.DTORequestMaintenance;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data  // genera automáticamente los métodos getter, setter, toString(), equals(), y hashCode()
@NoArgsConstructor // genera automáticamente el contructor vacio
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column (nullable = false)
    private Timestamp start_date;

    @Column
    private Timestamp end_date;

    @Column (nullable = false)
    private Long scooter_id;

    @Column (nullable = false)
    private Long scooter_station_id;

    public Maintenance(DTORequestMaintenance request) {
        this.id = request.getId();
        this.start_date = request.getStart_date();
        this.end_date = request.getEnd_date();
        this.scooter_id = request.getScooter_id();
        this.scooter_station_id = request.getScooter_station_id();
    }

}
