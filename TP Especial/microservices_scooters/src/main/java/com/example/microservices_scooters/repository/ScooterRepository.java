package com.example.microservices_scooters.repository;

import com.example.microservices_scooters.dto.DTORespondeStatusQualityScooter;
import com.example.microservices_scooters.entity.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter,Long> {

    @Query("SELECT s.id,s.model, CASE WHEN :ordering = 'kilometros' THEN s.kmsTraveled WHEN :ordering = 'tiempo-sin-pausa' THEN (s.totalUsageTime-s.pausedTime) ELSE s.totalUsageTime END FROM Scooter s ORDER BY 3 DESC")
    List<Object[]> getReport(String ordering);
    @Query("SELECT s.id,s.model,s.kmsTraveled, CASE WHEN :withPause = true THEN s.totalUsageTime ELSE (s.totalUsageTime-s.pausedTime) END FROM Scooter s ORDER BY s.kmsTraveled DESC")
    List<Object[]> getScootersOfKms(boolean withPause);
    @Query("SELECT s FROM Scooter s JOIN Ride r ON (r.scooter.id = s.id) WHERE s.numberOfTrips >= :cant and YEAR(r.initiated) = :anio GROUP BY s.id")
    List<Scooter> getBySearch(int cant, int anio);
    @Query("SELECT new com.example.microservices_scooters.dto.DTORespondeStatusQualityScooter( SUM(CASE WHEN s.state = :en_uso THEN 1 ELSE 0 END), SUM(CASE WHEN s.state = :mantenimiento THEN 1 ELSE 0 END)) FROM Scooter s")
    //@Query("SELECT s.state, count(*) FROM Scooter s WHERE s.state = :en_uso or s.state = :mantenimiento group by s.state")
    DTORespondeStatusQualityScooter getQuantityBasedOnStatus(String en_uso, String mantenimiento);
    @Query("SELECT s FROM Scooter s WHERE s.location.latitud-5.0 <= :latitud and s.location.latitud+5.0 >= :latitud and s.location.longitud-5.0 <= :longitud and s.location.longitud+5.0 >= :longitud")
    List<Scooter> getScootersSurroundings(double longitud, double latitud);

}
