package com.example.microservices_admin_maintenance.repository;

import com.example.microservices_admin_maintenance.entity.Fare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FareRepository extends MongoRepository<Fare, String> {

}
