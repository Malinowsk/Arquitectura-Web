package com.example.microservices_scooters.dto;

import com.example.microservices_scooters.entity.GPS;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class DTORequestStation {
    @Min(value = 0, message = "ID must be greater than 0")
    @NotNull(message = "ID cannot be null")
    private long  id;
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "the name cannot be empty")
    private String name;
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "the name cannot be empty")
    private GPS location;
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "the name cannot be empty")
    private int cantMaxSkateboards;
}
