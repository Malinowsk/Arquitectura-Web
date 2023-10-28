package com.example.microservices_admin_maintenance.entity;

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

    @Column
    private Timestamp start_date;

    @Column
    private Timestamp end_date;

    @Column
    @OneToOne
    private Scooter scooter;

    @Column
    @OneToOne
    private Station scooter_station;

}
