package main.resources.tp2.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "career")
    private List<Inscription> students;

    public Career(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Inscription> getStudents() {
        ArrayList<Inscription> copy = new ArrayList(Collections.unmodifiableList(students));
        return copy;
    }

    public void setStudents(List<Inscription> students) {
        this.students = students;
    }
}
