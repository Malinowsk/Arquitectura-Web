package com.example.microservices_admin_maintenance.dto;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DTORequestScooterModel {

    private String model;
    public DTORequestScooterModel() {}
    public DTORequestScooterModel(String model) {
        this.model = model;
    }

}
