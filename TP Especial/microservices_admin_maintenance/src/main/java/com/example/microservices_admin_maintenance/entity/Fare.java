package com.example.microservices_admin_maintenance.entity;


import com.example.microservices_admin_maintenance.dto.DTOFareRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data  // genera automáticamente los métodos getter, setter, toString(), equals(), y hashCode()
@NoArgsConstructor // genera automáticamente el contructor vacio
public class Fare {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private double cost_per_min;

    @Column
    private double extended_pause_cost;

    public Fare(String name, double cost, double extended_pause_cost) {
        this.name = name;
        this.cost_per_min = cost;
        this.extended_pause_cost = extended_pause_cost;
    }

    public Fare(DTOFareRequest fDTO) {
        this.name = fDTO.getName();
        this.cost_per_min = fDTO.getCost_per_min();
        this.extended_pause_cost = fDTO.getExtended_pause_cost();
    }
}
