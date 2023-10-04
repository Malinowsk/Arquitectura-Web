package com.example.trabajoentregable3.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class DTOCareer {

    @Column
    private String name;

    public DTOCareer(String name){
        this.name = name;
    }

}
