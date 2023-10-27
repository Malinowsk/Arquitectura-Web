package com.example.microservices_scooters.repository;

import com.example.microservices_scooters.entity.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryScooter extends JpaRepository<Scooter,Long> {

}
