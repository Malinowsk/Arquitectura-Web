package com.example.trabajoentregable3.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class DTOInscription {

    private Timestamp fecha_ingreso;
    private Timestamp fecha_egreso;
    private int student_notebook_number;
    private int career_id;

    public DTOInscription(Timestamp fecha_ingreso, Timestamp fecha_egreso, int student_notebook_number, int career_id) {
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_egreso = fecha_egreso;
        this.student_notebook_number = student_notebook_number;
        this.career_id = career_id;
    }

}
