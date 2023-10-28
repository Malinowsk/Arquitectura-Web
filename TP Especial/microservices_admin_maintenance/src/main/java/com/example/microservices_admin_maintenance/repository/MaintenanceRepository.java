package com.example.microservices_admin_maintenance.repository;

import com.example.microservices_admin_maintenance.entity.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {

}
