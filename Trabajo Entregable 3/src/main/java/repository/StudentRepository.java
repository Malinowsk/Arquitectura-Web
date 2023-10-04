package repository;

import dto.DTOStudent;
import entity.Student;

import java.util.List;

public interface StudentRepository {

    public void save(Student s);
    public List<DTOStudent> getAll();
    public DTOStudent getById(long id);
    public List<DTOStudent> getByGender(String gender);
    public List<DTOStudent> getByCareerAndCity(Long idCarrera, String ciudad);

}
