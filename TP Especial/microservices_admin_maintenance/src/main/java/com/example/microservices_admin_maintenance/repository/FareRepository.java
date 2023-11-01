package com.example.microservices_admin_maintenance.repository;

import com.example.microservices_admin_maintenance.entity.Fare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FareRepository extends JpaRepository<Fare, Long> {

}
