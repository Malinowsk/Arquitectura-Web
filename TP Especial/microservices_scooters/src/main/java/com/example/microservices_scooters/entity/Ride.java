package com.example.microservices_scooters.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data  // genera automáticamente los métodos getter, setter, toString(), equals(), y hashCode()
@NoArgsConstructor // genera automáticamente el contructor vacio
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long  id;
    @Column
    private long  id_user;
    @Column
    private long  id_account;
    @Column
    private Timestamp initiated;
    @Column
    private Timestamp finalized;
    @Column
    private float kilometers_traveled;
    @Column
    private float total_price;

}
