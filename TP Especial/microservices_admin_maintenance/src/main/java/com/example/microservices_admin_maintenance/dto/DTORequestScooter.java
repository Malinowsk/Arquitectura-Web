package com.example.microservices_admin_maintenance.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Getter
public class DTORequestScooter {

    @Min(value = 0, message = "ID must be greater than 0")
    @NotNull(message = "ID cannot be null")
    private Long  id;

    private String state = "disponible";

    private double kmsTraveled = 0;

    private double kmsMant = 0;

    private Long totalUsageTime = 0L;

    private Long pausedTime = 0L;

    private int numberOfTrips = 0;

    private String model;
}
