package com.example.microservices_scooters.dto;

import com.example.microservices_scooters.entity.GPS;
import com.example.microservices_scooters.entity.Scooter;

public class DTOResponseReport {

    private Long  id;
    private String model;
    private  Object valor;

    public DTOResponseReport(Long id, String model, Object valor) {
        this.id = id;
        this.model = model;
        this.valor = valor;
    }
}
