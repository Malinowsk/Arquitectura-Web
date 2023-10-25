package com.example.trabajoentregable3.repository;

import com.example.trabajoentregable3.dto.DTOResponseStudent;
import com.example.trabajoentregable3.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByOrderBySurnameAsc();
    List<Student> getStudentsByGender(String genero);

    @Query(value = "SELECT s FROM Student s, IN(s.registrations) r WHERE r.career.id = :careerId AND s.city = :city")
    List<Student> getStudentByCityAndCareer(@Param("careerId") Long idCarrera, @Param("city") String city);

}
