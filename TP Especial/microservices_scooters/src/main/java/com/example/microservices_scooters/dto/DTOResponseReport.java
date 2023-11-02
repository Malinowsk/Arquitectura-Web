package com.example.microservices_scooters.dto;

import com.example.microservices_scooters.entity.GPS;
import com.example.microservices_scooters.entity.Scooter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class DTOResponseReport {

    private Long  id;
    private String model;
    private  Long valor;

    public DTOResponseReport(Long id, String model, Double valor) {
        this.id = id;
        this.model = model;
        this.valor = Double.valueOf(valor).longValue();
    }
}
