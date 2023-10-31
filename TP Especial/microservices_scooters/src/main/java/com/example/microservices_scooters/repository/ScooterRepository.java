package com.example.microservices_scooters.repository;

import com.example.microservices_scooters.dto.DTOResponseScooter;
import com.example.microservices_scooters.entity.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter,Long> {

    @Query("SELECT s FROM Scooter s ORDER BY :ordering DESC")
    List<DTOResponseScooter> getReport(String ordering);
}
