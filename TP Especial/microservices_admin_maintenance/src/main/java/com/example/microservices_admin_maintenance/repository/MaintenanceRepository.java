package com.example.microservices_admin_maintenance.repository;

import com.example.microservices_admin_maintenance.entity.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface MaintenanceRepository extends MongoRepository<Maintenance, String> {

}
