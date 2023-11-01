package com.example.microservices_scooters.entity;

import com.example.microservices_scooters.dto.DTORequestRide;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data  // genera automáticamente los métodos getter, setter, toString(), equals(), y hashCode()
@NoArgsConstructor // genera automáticamente el contructor vacio
public class Ride implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //auto o entity?
    private long  id;
    @ManyToOne
    @JoinColumn(name = "id_scooter")
    private Scooter scooter;
    @Column
    private long  idUser;
    @Column
    private long  idAccount;
    @Column
    private Timestamp initiated;
    @Column
    private Timestamp finalized;
    @Column
    private double kilometersTraveled;
    @Column
    private float totalPrice;
    @Column
    private Timestamp pauseTime; //en segundos
    @Column
    private boolean activePause;
    //ver
    @ManyToOne
    @JoinColumn(name = "id_station")
    private Station finalStation ;

    //@Temporal(TemporalType.TIMESTAMP)
    //@Column
    //private Date inicioViaje;

    //@Temporal(TemporalType.TIMESTAMP)
    //@Column
    //private Date finViaje;
    public Ride(Scooter scooter, long idUser, long idAccount) {
        this.scooter = scooter;
        this.idUser = idUser;
        this.idAccount = idAccount;
        this.initiated = new Timestamp(System.currentTimeMillis());
    }

    public Ride(DTORequestRide r) {

    }


}
