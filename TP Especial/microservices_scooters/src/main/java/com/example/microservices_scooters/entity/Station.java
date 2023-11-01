package com.example.microservices_scooters.entity;


import com.example.microservices_scooters.dto.DTORequestScooter;
import com.example.microservices_scooters.dto.DTORequestStation;
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

    @Embedded
    private GPS location;

    @OneToMany
    private List<Scooter> skateboards;

    @Column
    private int cantMaxSkateboards;

    public Station(String name, GPS location, List<Scooter> skateboards, int cantMaxSkateboards) {
        this.name = name;
        this.location = location;
        this.skateboards = skateboards;
        this.cantMaxSkateboards = cantMaxSkateboards;
    }

    public Station(DTORequestStation s){
        this.id = s.getId();
        this.name = s.getName();
        this.location = s.getLocation();
        this.cantMaxSkateboards = s.getCantMaxSkateboards();
    }

    public boolean addScooterToStation( Scooter s){
        if (s.getLocation().equals(this.location) && !skateboards.contains(s)) {
            this.skateboards.add(s);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean removeScooterToStation( Scooter s){
            return this.skateboards.remove(s);
    }
}
