package com.example.trabajoentregable3.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Data
public class DTORequestStudent {

    @Min(value = 0, message = "ID must be greater than 0")
    @NotNull(message = "ID cannot be null")
    private long  universityNotebook;
    @NotNull(message = "Cannot be null")
    private int documentNumber;
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "the name cannot be empty")
    private String name;
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "the name cannot be empty")
    private String surname;
    @NotNull(message = "Cannot be null")
    private Timestamp birthdate;
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "the name cannot be empty")
    private String gender;
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "the name cannot be empty")
    private String city;
}
