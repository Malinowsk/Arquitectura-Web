package com.example.trabajoentregable3.repository;

import com.example.trabajoentregable3.dto.DTONumberRegisteredPerCareer;
import com.example.trabajoentregable3.entity.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface CareerRepository extends JpaRepository<Career, Long> {

    @Query(value = "SELECT new DTONumberRegisteredPerCareer(c.name, COUNT(s)) FROM Career c JOIN c.students s GROUP BY c.name ORDER BY COUNT(s) DESC", nativeQuery = true)
    public List<DTONumberRegisteredPerCareer> getCareerOrderByQuantityStudent();
}
