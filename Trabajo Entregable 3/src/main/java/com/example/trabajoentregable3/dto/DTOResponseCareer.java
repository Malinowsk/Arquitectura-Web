package com.example.trabajoentregable3.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class DTOResponseCareer {

    @Column
    private int id;

    @Column
    private String name;

    public DTOResponseCareer(int id, String name){
        this.id = id;
        this.name = name;
    }

}
