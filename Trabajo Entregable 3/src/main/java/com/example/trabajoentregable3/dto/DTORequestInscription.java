package com.example.trabajoentregable3.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Data
@RequiredArgsConstructor
public class DTORequestInscription {
    @NotNull(message = "The id cannot be null")
    private final long career_id;
    @NotNull(message = "The id cannot be null")
    private final long student_notebook_number;
    @NotNull(message = "The inscription cannot be null")
    private final Timestamp fecha_ingreso;
    @NotNull(message = "The graduation cannot be null")
    private final Timestamp fecha_egreso;
}
