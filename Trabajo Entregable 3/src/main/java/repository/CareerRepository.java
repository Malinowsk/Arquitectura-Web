package repository;

import dto.DTONumberRegisteredPerCareer;
import entity.Career;
import entity.Inscription;

import java.util.List;

public interface CareerRepository {

    public void save(Career c);
    public Career getById(long id);
    public List<DTONumberRegisteredPerCareer> getCareerOrderByQuantityStudent();

}
