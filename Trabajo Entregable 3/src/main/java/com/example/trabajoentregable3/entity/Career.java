package com.example.trabajoentregable3.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Data  // genera automáticamente los métodos getter, setter, toString(), equals(), y hashCode()
@NoArgsConstructor // genera automáticamente el contructor vacio
public class Career {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "career")
    private List<Inscription> students;

    public Career(String name) {
        this.name = name;
    }

    public Career(long id) {
        this.id = id;
    }

    public List<Inscription> getStudents() {
        ArrayList<Inscription> copy = new ArrayList(Collections.unmodifiableList(students));
        return copy;
    }

    public void setStudents(List<Inscription> students) {
        this.students = students;
    }


}
