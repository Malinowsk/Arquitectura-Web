package com.example.microservices_scooters.repository;

import com.example.microservices_scooters.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station,Long> {
}
