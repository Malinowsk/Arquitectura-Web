package com.example.trabajoentregable3.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Entity
public class Inscription implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn
    private Career career;


    @Column (name = "fecha_inscripcion")
    private Timestamp fecha_ingreso;


    @Column(name = "fecha_egreso", nullable = true)
    private Timestamp fecha_egreso;

    public Inscription() {
    }

    public Inscription(Career career, Student student, Timestamp fecha_ingreso, Timestamp fecha_egreso) {
        this.career = career;
        this.student = student;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_egreso = fecha_egreso;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Career getCareer() {
        return career;
    }

    public void setCareer(Career career) {
        this.career = career;
    }

    public Timestamp getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Timestamp fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public Timestamp getFecha_egreso() {
        return fecha_egreso;
    }

    public void setFecha_egreso(Timestamp fecha_egreso) {
        this.fecha_egreso = fecha_egreso;
    }


    public int getAntiguedad() {
        LocalDateTime localDateTime = fecha_ingreso.toLocalDateTime();

        LocalDateTime currentDate = LocalDateTime.now();
        long monthsDifference = ChronoUnit.MONTHS.between(localDateTime, currentDate);

        return (int) monthsDifference;
    }
    @Override
    public String toString() {
        return "\nInscription{" +
                "Estudiante=" + student +
                ", Carrera=" + career +
                ", Fecha de ingreso=" + fecha_ingreso +
                ", Fecha de egreso=" + fecha_egreso +
                ", Antiguedad=" + getAntiguedad() + " meses"+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inscription that = (Inscription) o;
        return Objects.equals(student, that.student) && Objects.equals(career, that.career);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, career);
    }
}

