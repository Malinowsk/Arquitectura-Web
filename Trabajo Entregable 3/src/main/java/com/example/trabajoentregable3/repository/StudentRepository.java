package com.example.trabajoentregable3.repository;

import com.example.trabajoentregable3.dto.DTOStudent;
import com.example.trabajoentregable3.entity.Student;

import java.util.List;

public interface StudentRepository {

    public void save(Student s);
    public List<DTOStudent> getAll();
    public DTOStudent getById(long id);
    public List<DTOStudent> getByGender(String gender);
    public List<DTOStudent> getByCareerAndCity(Long idCarrera, String ciudad);

}
