package main.resources.tp2.repository;

import main.resources.tp2.entity.Inscription;
import main.resources.tp2.entity.Student;

import java.util.List;

public interface StudentRepository {


    public void save(Student s);
    public List<Student> getAll();
    public Student getById(long id);
    public List<Student> getByGender(String gender);
    public List<Student> getByCarrerAndCity(Long idCarrera, String ciudad);

}
