package com.example.microservices_admin_maintenance.service;

import com.example.microservices_admin_maintenance.dto.*;
import com.example.microservices_admin_maintenance.entity.Maintenance;
import com.example.microservices_admin_maintenance.exception.NotFoundException;
import com.example.microservices_admin_maintenance.repository.MaintenanceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceService {

    private final RestTemplate restTemplate;

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

        DTORequestScooter sDTO = new DTORequestScooter();
        sDTO.setState("en mantenimiento");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<DTORequestScooter> requestEntity = new HttpEntity<>(sDTO, headers);
        String uri = "http://localhost:8003/api/monopatines/"+request.getScooter_id()+"/paradas/"+request.getScooter_station_id();
        ResponseEntity<String> exchangeResult = restTemplate.exchange(uri, HttpMethod.PUT, requestEntity, String.class);

        System.out.println(exchangeResult);

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

        //Falta chequear que no vengan datos null
        maintenance.setScooter_id(request.getScooter_id());
        maintenance.setScooter_station_id(request.getScooter_station_id());
        maintenance.setStart_date(request.getStart_date());
        maintenance.setEnd_date(request.getEnd_date());

        this.maintenanceRepository.save(maintenance);
        return buildMaintenanceDTO(maintenance);
    }

    @Transactional
    public String endScooterMaintenance(Long id) {
        DTORequestScooter sDTO = new DTORequestScooter();
        sDTO.setState("disponible");

        //DTORequestScooter con estado en disponible

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<DTORequestScooter> requestEntity = new HttpEntity<>(sDTO, headers);
        return null;

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
