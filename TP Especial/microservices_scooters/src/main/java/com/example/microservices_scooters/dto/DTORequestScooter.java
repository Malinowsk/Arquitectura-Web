package com.example.microservices_scooters.dto;

import com.example.microservices_scooters.entity.GPS;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@RequiredArgsConstructor
@Data
public class DTORequestScooter {

    @Min(value = 0, message = "ID must be greater than 0")
    @NotNull(message = "ID cannot be null")
    private Long  id;

    private String state = "disponible";

    private GPS location;

    private double kmsTraveled = 0;

    private double kmsMant = 0;

    private Long totalUsageTime = 0L;

    private Long pausedTime = 0L;

    private int numberOfTrips = 0;

    private String model;


}
