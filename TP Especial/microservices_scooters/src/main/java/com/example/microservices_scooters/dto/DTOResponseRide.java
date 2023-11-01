package com.example.microservices_scooters.dto;

import com.example.microservices_scooters.entity.GPS;
import com.example.microservices_scooters.entity.Ride;
import com.example.microservices_scooters.entity.Scooter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class DTOResponseRide {

    private long  id;
    private Scooter scooter;
    private long  idUser;
    private long  idAccount;
    private Timestamp initiated;
    private Timestamp finalized;
    private double kilometersTraveled;
    private float totalPrice;
    private Timestamp pauseTime;
    private boolean activePause;
    private long id_tarifa;

    public DTOResponseRide(Ride r ) {
        this.id = r.getId();
        this.scooter = r.getScooter();
        this.idUser = r.getIdUser();
        this.idAccount = r.getIdAccount();
        this.initiated = r.getInitiated();
        this.finalized = r.getFinalized();
        this.kilometersTraveled = r.getKilometersTraveled();
        this.totalPrice = r.getTotalPrice();
        this.pauseTime = r.getPauseTime();
        this.activePause = r.isActivePause();
        this.id_tarifa = r.getId_tarifa();
    }

}
