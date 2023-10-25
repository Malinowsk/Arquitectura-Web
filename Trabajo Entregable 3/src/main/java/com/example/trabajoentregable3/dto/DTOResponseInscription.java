package com.example.trabajoentregable3.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class DTOResponseInscription {

    private int career_id;
    private int student_notebook_number;
    private Timestamp fecha_ingreso;
    private Timestamp fecha_egreso;


    public DTOResponseInscription(Timestamp fecha_ingreso, Timestamp fecha_egreso, int student_notebook_number, int career_id) {
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_egreso = fecha_egreso;
        this.student_notebook_number = student_notebook_number;
        this.career_id = career_id;
    }

}
