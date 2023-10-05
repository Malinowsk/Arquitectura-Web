package com.example.trabajoentregable3.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Data
public class DTORequestCareer {
    @NotNull(message = "the name cannot be null")
    @NotEmpty(message = "the name cannot be empty")
    private final String name;
}
