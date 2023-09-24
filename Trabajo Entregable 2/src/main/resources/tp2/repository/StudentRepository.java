package main.resources.tp2.repository;

import main.resources.tp2.dto.DTOStudent;
import main.resources.tp2.entity.Inscription;
import main.resources.tp2.entity.Student;

import java.util.List;

public interface StudentRepository {


    public void save(Student s);
    public List<DTOStudent> getAll();
    public Student getById(long id);
    public List<DTOStudent> getByGender(String gender);
    public List<DTOStudent> getByCareerAndCity(Long idCarrera, String ciudad);

}
