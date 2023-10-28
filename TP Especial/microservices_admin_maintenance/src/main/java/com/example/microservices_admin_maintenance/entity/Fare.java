package com.example.microservices_admin_maintenance.entity;


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
    private double cost;

}
