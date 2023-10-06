package com.example.trabajoentregable3.repository;


import com.example.trabajoentregable3.entity.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Long> {
    @Query("SELECT i FROM Inscription i WHERE i.id.universityNotebook = ?1 and i.id.idCareer=?2")
    boolean existsByIdCompuesta(long studentNotebookNumber, long careerId);
}
