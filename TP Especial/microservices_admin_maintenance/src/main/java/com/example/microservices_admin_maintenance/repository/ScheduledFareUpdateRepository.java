package com.example.microservices_admin_maintenance.repository;

import com.example.microservices_admin_maintenance.entity.ScheduledFareUpdate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ScheduledFareUpdateRepository extends MongoRepository<ScheduledFareUpdate, String> {

    List<ScheduledFareUpdate> findScheduledFareUpdatesByDateContaining(String date);

    List<ScheduledFareUpdate> findScheduledFareUpdatesByDateBefore(String date);

}
