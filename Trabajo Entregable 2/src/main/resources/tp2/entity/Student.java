package main.resources.tp2.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
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

    @Column
    private String gender;

    @Column
    private String city;

    @OneToMany (mappedBy = "student")
    private List<Inscription> registrations;

    public Student(int documentNumber, String name, String surname, Timestamp birthdate, String gender, String city) {
        this.documentNumber = documentNumber;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.city = city;

    }
    public Student(int documentNumber, String name, String surname, Timestamp birthdate, String gender, String city, List<Inscription> registrations) {
        this.documentNumber = documentNumber;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.city = city;
        this.registrations = registrations;
    }

    public Student() {

    }

    public long getUniversityNotebook() {
        return universityNotebook;
    }

    public int getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(int documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Timestamp getBirthdate() {
        return birthdate;  // devolver la edad
    }

    public void setBirthdate(Timestamp birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ArrayList<Inscription> getRegistrations() {
        ArrayList<Inscription> copy = new ArrayList(Collections.unmodifiableList(registrations));
        return copy;
    }

    public void setRegistrations(List<Inscription> registrations) {
        this.registrations = registrations;
    }



    @Override
    public String toString() {
        return "\n Libreta Universitaria: " + universityNotebook +
                ", DNI: " + documentNumber +
                ", Nombre: '" + name + '\'' +
                ", Apellido: '" + surname + '\'' +
                ", Fecha de nacimiento: " + birthdate +
                ", Sexo: '" + gender + '\'' +
                ", Ciudad: '" + city+"'";
    }

}
