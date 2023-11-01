package com.example.microservices_scooters.repository;

import com.example.microservices_scooters.dto.DTOResponseCharged;
import com.example.microservices_scooters.entity.Ride;
import com.example.microservices_scooters.entity.Scooter;
import com.example.microservices_scooters.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface RideRepository extends JpaRepository<Ride,Long> {

    @Query(value = "SELECT sum(r.total_price) FROM Ride r WHERE year(r.initiated) = :anio AND month(r.initiated) >= :mesInicio AND month(r.initiated) <= :mesFin",nativeQuery = true)
    List<Float> getTotalCharged(int anio, int mesInicio, int mesFin);

}
