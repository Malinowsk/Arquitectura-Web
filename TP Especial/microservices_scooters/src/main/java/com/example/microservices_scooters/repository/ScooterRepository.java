package com.example.microservices_scooters.repository;

import com.example.microservices_scooters.dto.DTORespondeStatusQualityScooter;
import com.example.microservices_scooters.dto.DTOResponseReport;
import com.example.microservices_scooters.dto.DTOResponseScooter;
import com.example.microservices_scooters.entity.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter,Long> {

    @Query("SELECT s.id,s.model, CASE WHEN :ordering = 'kilometros' THEN s.kmsTraveled WHEN :ordering = 'tiempo-sin-pausa' THEN (s.totalUsageTime-s.pausedTime) ELSE s.totalUsageTime END FROM Scooter s ORDER BY 3 DESC")
    List<DTOResponseReport> getReportKmsOrTt(String ordering);

    //@Query("SELECT s.id,s.model, (s.totalUsageTime-s.) FROM Scooter s ORDER BY CASE WHEN :ordering = 'kilometros' THEN s.kmsTraveled ELSE s.totalUsageTime END DESC")
    //List<DTOResponseReport> getReportTimeWithoutPause();
    @Query("SELECT s FROM Scooter s JOIN Ride r ON (r.scooter.id = s.id) WHERE s.numberOfTrips >= :cant and YEAR(r.initiated) = :anio GROUP BY s.id")
    List<Scooter> getBySearch(int cant, int anio);
    @Query("SELECT s.state, count(*)FROM Scooter s WHERE s.state = :en_uso or s.state = :mantenimiento group by s.state")
    DTORespondeStatusQualityScooter getQuantityBasedOnStatus(String en_uso, String mantenimiento);
    @Query("SELECT s FROM Scooter s WHERE s.location.latitud-30 <= :latitud and s.location.latitud+30 <= :latitud and s.location.longitud-30 <= :longitud and s.location.longitud+30 <= :longitud")
    List<Scooter> getScootersSurroundings(double longitud, double latitud);
}
