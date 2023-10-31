package com.example.microservices_admin_maintenance.dto;

import com.example.microservices_admin_maintenance.entity.GPS;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
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
    @NotNull(message = "Cannot be null")
    private String state;
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "the name cannot be empty")
    private GPS location;
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "the name cannot be empty")
    private double kmsTraveled;
    @NotNull(message = "Cannot be null")
    private double kmsMant;
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "the name cannot be empty")
    private Long totalUsageTime;
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "the name cannot be empty")
    private Long pausedTime;
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "the name cannot be empty")
    private Long numberOfTrips;
}
