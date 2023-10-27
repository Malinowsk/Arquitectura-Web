package com.example.microservices_scooters.entity;


import com.example.microservices_scooters.entity.Scooter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data  // genera automáticamente los métodos getter, setter, toString(), equals(), y hashCode()
@NoArgsConstructor // genera automáticamente el contructor vacio
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long  id;

    @Column
    private String name;

    //@Embedded
    //private GPS ubicacion;

    @OneToMany
    private List<Scooter> skateboards;

    public Station(String name) {
        this.name = name;
    }
}
