package com.example.trabajoentregable3.repository;

import com.example.trabajoentregable3.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByOrderBySurnameAsc();
    List<Student> getStudentsByGender(String genero);

}
