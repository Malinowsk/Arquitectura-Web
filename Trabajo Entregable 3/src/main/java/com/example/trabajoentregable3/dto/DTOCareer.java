package com.example.trabajoentregable3.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class DTOCareer {

    @Column
    private int id;

    @Column
    private String name;

    public DTOCareer(int id, String name){
        this.id = id;
        this.name = name;
    }

}
