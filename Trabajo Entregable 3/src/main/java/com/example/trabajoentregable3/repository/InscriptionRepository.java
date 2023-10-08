package com.example.trabajoentregable3.repository;


import com.example.trabajoentregable3.entity.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Long> {
   // @Query("SELECT i FROM Inscription i WHERE i.id.universityNotebook = ?1 and i.id.idCareer=?2")
    //boolean existsByIdCompuesta(long studentNotebookNumber, long careerId);

    @Query("SELECT COUNT(i) > 0 FROM Inscription i WHERE i.student.universityNotebook = :studentId AND i.career.id = :careerId")
    boolean existsByStudentIdAndCareerId(@Param("studentId") Long studentId, @Param("careerId") Long careerId);

    @Query(value="SELECT fj.name, fj.year, SUM(fj.enrolled), SUM(fj.graduated)\n" +
            "FROM\n" +
            "\t(\n" +
            "        SELECT c.name, YEAR(i.fecha_inscripcion) as year, COUNT(*) as enrolled, '0' as graduated\n" +
            "        FROM inscription i JOIN career c ON c.id = i.career_id\n" +
            "        GROUP BY c.name, year\n" +
            "        UNION\n" +
            "        SELECT c.name, year(i.fecha_egreso) as year, '0' as enrolled, COUNT(*) as graduated\n" +
            "        FROM inscription i JOIN career c ON c.id = i.career_id\n" +
            "        GROUP BY c.name, year HAVING year IS NOT NULL\n" +
            "\t) as fj\n" +
            "GROUP BY fj.name, fj.year\n" +
            "ORDER BY fj.name, fj.year", nativeQuery = true)
    List<Object[]> getReport();
}
