package main.resources.tp2.repository;

import main.resources.tp2.entity.Career;
import main.resources.tp2.entity.Inscription;

import java.util.List;

public interface CareerRepository {

    public void save(Career c);
    public Career getById(long id);
    public List<List<Career>> getCareerOrderByQuantityStudent();

}
