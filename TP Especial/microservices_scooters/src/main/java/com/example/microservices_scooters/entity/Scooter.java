package com.example.microservices_scooters.entity;

import com.example.microservices_scooters.dto.DTORequestScooter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
    private Long totalUsageTime; //en segundos

    //iria un tiempo pausado???
    @Column
    private Long pausedTime; //en segundos

    @Column
    private Long numberOfTrips;

public Scooter(DTORequestScooter s){
    this.id = s.getId();
    this.location = s.getLocation();
    this.state = s.getState();
    this.kmsTraveled = s.getKmsTraveled();
    this.kmsMant = s.getKmsMant();
    this.totalUsageTime = s.getTotalUsageTime();
    this.pausedTime = s.getPausedTime();
    this.numberOfTrips = s.getNumberOfTrips();
}

}
