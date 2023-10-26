package com.example.microservices_scooters.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data  // genera automáticamente los métodos getter, setter, toString(), equals(), y hashCode()
@NoArgsConstructor // genera automáticamente el contructor vacio
public class Scooter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long  id;

    @Column
    private String state;

    @OneToMany
    private List<Ride> rides;
}
