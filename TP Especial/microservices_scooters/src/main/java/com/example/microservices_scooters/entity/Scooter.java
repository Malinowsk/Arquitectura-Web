package com.example.microservices_scooters.entity;

import com.example.microservices_scooters.dto.DTORequestScooter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Data  // genera automáticamente los métodos getter, setter, toString(), equals(), y hashCode()
@NoArgsConstructor // genera automáticamente el contructor vacio
public class Scooter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_scooter")
    private Long  id;

    //esta en uso-disponible-enMantenimiento
    @Column
    private String state;

    @Embedded
    private GPS location;

    //@Column
    //private boolean disponible;

    @Column
    private double kmsTraveled;

    //va aumentando a cada viaje, cuando entra a ment se setea a cero
    @Column
    private double kmsMant;

    //definimos q a los 100km se le haga mant
    @Transient
    private double numbKmsForMaint = 100;

    //va aumentando a cada viaje
    @Column
    private long totalUsageTime; //en segundos

    //iria un tiempo pausado???
    @Column
    private long pausedTime; //en segundos

    @Column
    private String model;

    public Scooter(String model) {
        this.state = "disponible";
        this.location = new GPS(0.0,0.0);
        this.kmsTraveled = 0;
        this.kmsMant = 0;
        this.totalUsageTime = 0L;
        this.pausedTime = 0L;
        this.model = model;
    }

    public Scooter(DTORequestScooter s){
    this.id = s.getId();
    this.location = new GPS(0.0,0.0);
    this.state = s.getState();
    this.kmsTraveled = s.getKmsTraveled();
    this.kmsMant = s.getKmsMant();
    this.totalUsageTime = s.getTotalUsageTime();
    this.pausedTime = s.getPausedTime();
    this.model = s.getModel();
}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scooter scooter = (Scooter) o;
        return Objects.equals(id, scooter.id);
    }

}
