package com.example.microservices_scooters.repository;

import com.example.microservices_scooters.dto.DTORespondeStatusQualityScooter;
import com.example.microservices_scooters.dto.DTOResponseScooter;
import com.example.microservices_scooters.entity.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter,Long> {

    @Query("SELECT s FROM Scooter s ORDER BY CASE WHEN :ordering = 'kilometros' THEN s.kmsTraveled ELSE s.totalUsageTime END DESC")
    List<DTOResponseScooter> getReport(String ordering);

    @Query("SELECT s FROM Scooter s JOIN Ride r ON (r.scooter.id = s.id) WHERE s.numberOfTrips >= :cant and r.initiated = :anio GROUP BY s.id")
    List<Scooter> getBySearch(int cant, int anio);

    @Query("SELECT s.state, count(*)FROM Scooter s WHERE s.state = :en_uso or s.state = :mantenimiento group by s.state")
    DTORespondeStatusQualityScooter getQuantityBasedOnStatus(String en_uso, String mantenimiento);

}
