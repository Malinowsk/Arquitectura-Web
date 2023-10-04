package com.example.trabajoentregable3.repository;

import com.example.trabajoentregable3.dto.DTONumberRegisteredPerCareer;
import com.example.trabajoentregable3.entity.Career;

import java.util.List;

public interface CareerRepository {

    public void save(Career c);
    public Career getById(long id);
    public List<DTONumberRegisteredPerCareer> getCareerOrderByQuantityStudent();

}
