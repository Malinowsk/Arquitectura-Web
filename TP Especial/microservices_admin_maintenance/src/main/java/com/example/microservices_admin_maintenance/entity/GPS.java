package com.example.microservices_admin_maintenance.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data  // genera automáticamente los métodos getter, setter, toString(), equals(), y hashCode()
@NoArgsConstructor // genera automáticamente el contructor vacio
public class GPS {
    private double latitud;
    private double longitud;

    public GPS(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

}