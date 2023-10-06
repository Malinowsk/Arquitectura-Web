package com.example.trabajoentregable3.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Entity
@Data  // genera automáticamente los métodos getter, setter, toString(), equals(), y hashCode()
@NoArgsConstructor // genera automáticamente el contructor vacio
public class Inscription{

    @EmbeddedId
    private InscriptionId id;

    @ManyToOne(targetEntity = Student.class)
    @MapsId("universityNotebook")
    @JoinColumn(name = "university_notebook")
    private Student student;

    @ManyToOne (targetEntity = 	Career.class)
    @MapsId("idCareer")
    @JoinColumn(name = "career_id")
    private Career career;

    @Column (name = "fecha_inscripcion")
    private Timestamp fecha_ingreso;

    @Column(name = "fecha_egreso", nullable = true)
    private Timestamp fecha_egreso;

    public Inscription(Career career, Student student, Timestamp fecha_ingreso, Timestamp fecha_egreso) {
        this.career = career;
        this.student = student;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_egreso = fecha_egreso;
        this.id = new InscriptionId(career.getId(), student.getUniversityNotebook());
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

