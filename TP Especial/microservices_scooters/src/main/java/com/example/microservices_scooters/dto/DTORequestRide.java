package com.example.microservices_scooters.dto;

import com.example.microservices_scooters.entity.Scooter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Data
public class DTORequestRide {


    @Min(value = 0, message = "ID must be greater than 0")
    @NotNull(message = "ID cannot be null")
    private long  id;
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "the name cannot be empty")
    private Scooter scooter;
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "the name cannot be empty")
    private long  idUser;
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "the name cannot be empty")
    private long  idAccount;

    private Timestamp initiated;

    private Timestamp finalized;

    private double kilometersTraveled = 0.0;

    private float totalPrice = 0.0f;

    private Timestamp pauseTime;

    private boolean activePause = false;
}
