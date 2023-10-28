package com.example.microservices_admin_maintenance.service;

import com.example.microservices_admin_maintenance.entity.Maintenance;
import com.example.microservices_admin_maintenance.exception.NotFoundException;
import com.example.microservices_admin_maintenance.repository.MaintenanceRepository;
import com.example.microservices_admin_maintenance.dto.DTORequestMaintenance;
import com.example.microservices_admin_maintenance.dto.DTOResponseMaintenance;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;

    @Transactional
    public List<DTOResponseMaintenance> findAll() {
        return this.maintenanceRepository
                .findAll()
                .stream()
                .map(this::buildMaintenanceDTO)
                .toList();
    }

    @Transactional
    public DTOResponseMaintenance findById(Long id) {
        return this.maintenanceRepository
                .findById(id)
                .map(this::buildMaintenanceDTO)
                .orElseThrow( () -> new NotFoundException("Maintenance", id));
    }

    @Transactional
    public DTOResponseMaintenance save(DTORequestMaintenance request) {
        Maintenance maintenance = new Maintenance(request);
        Maintenance result = this.maintenanceRepository.save(maintenance);
        return new DTOResponseMaintenance(result);
    }

    @Transactional
    public void delete(Long id) {
        this.maintenanceRepository.delete(
                this.maintenanceRepository.findById(id)
                        .orElseThrow( () -> new NotFoundException("Maintenance", id)));
    }

    @Transactional
    public DTOResponseMaintenance update(Long id, DTORequestMaintenance request) {
        Maintenance maintenance = this.maintenanceRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Maintenance", id));

        maintenance.setEnd_date(request.getEnd_date());
        this.maintenanceRepository.save(maintenance);
        return buildMaintenanceDTO(maintenance);
    }

    private DTOResponseMaintenance buildMaintenanceDTO(Maintenance m) {
        return new DTOResponseMaintenance(
                m.getId(),
                m.getScooter_id(),
                m.getScooter_station_id(),
                m.getStart_date(),
                m.getEnd_date()
        );
    }

}
