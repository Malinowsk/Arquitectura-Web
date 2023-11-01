package com.example.microservices_scooters.repository;

import com.example.microservices_scooters.entity.Ride;
import com.example.microservices_scooters.entity.Scooter;
import com.example.microservices_scooters.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface RideRepository extends JpaRepository<Ride,Long> {

    @Query("SELECT sum(r.totalPrice)FROM Ride r WHERE year(r.initiated) = :anio and month (r.initiated) <= :mesInicio and month (r.finalized) >= :mesFin")
    float getTotalCharged(int anio, int mesInicio, int mesFin);

}
