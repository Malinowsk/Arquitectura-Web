package com.example.trabajoentregable3.repository;

import com.example.trabajoentregable3.entity.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface CareerRepository extends JpaRepository<Career, Long> {

    @Query(value = "SELECT c.name, count(s) FROM Career c LEFT JOIN c.students s GROUP BY c.name ORDER BY count(s) DESC")
    List<Object[]> getCareerOrderByQuantityStudent();


}
