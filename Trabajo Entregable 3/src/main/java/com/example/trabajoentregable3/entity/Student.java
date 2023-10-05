package com.example.trabajoentregable3.entity;

import com.example.trabajoentregable3.dto.DTOStudent;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Data  // genera automáticamente los métodos getter, setter, toString(), equals(), y hashCode()
@NoArgsConstructor // genera automáticamente el contructor vacio
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long  universityNotebook;

    @Column
    private int documentNumber;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private Timestamp birthdate;

    @Getter
    @Column
    private String gender;

    @Getter
    @Column
    private String city;

    @OneToMany (mappedBy = "student")
    private List<Inscription> registrations;

    public Student(long id) {
        this.universityNotebook = id;
    }

    public Student(int documentNumber, String name, String surname, Timestamp birthdate, String gender, String city) {
        this.documentNumber = documentNumber;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.city = city;
    }

    public ArrayList<Inscription> getRegistrations() {
        ArrayList<Inscription> copy = new ArrayList(Collections.unmodifiableList(registrations));
        return copy;
    }

    public void setRegistrations(List<Inscription> registrations) {
        this.registrations = registrations;
    }

    public int getAge() {
        LocalDate birthdateLocalDate = birthdate.toLocalDateTime().toLocalDate();
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthdateLocalDate, currentDate);
        return period.getYears();
    }

    @Override
    public String toString() {
        return "\n Nro LU: " + universityNotebook +
                ", DNI: " + documentNumber +
                ", Nombre: '" + name + '\'' +
                ", Apellido: '" + surname + '\'' +
                ", Edad: " + getAge() +
                ", Sexo: '" + gender + '\'' +
                ", Ciudad: '" + city+"'";
    }
}
