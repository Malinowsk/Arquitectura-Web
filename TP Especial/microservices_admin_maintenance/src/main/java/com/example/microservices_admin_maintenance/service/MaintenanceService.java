package com.example.microservices_admin_maintenance.service;

import com.example.microservices_admin_maintenance.dto.*;
import com.example.microservices_admin_maintenance.entity.Maintenance;
import com.example.microservices_admin_maintenance.exception.NotFoundException;
import com.example.microservices_admin_maintenance.repository.MaintenanceRepository;
import com.sun.tools.javac.Main;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    public DTOResponseMaintenance endScooterMaintenance(Long id) {
        Maintenance maintenance = this.maintenanceRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Maintenance", id));

        maintenance.setEnd_date(Timestamp.valueOf(LocalDateTime.now()));

        DTORequestScooter sDTO = new DTORequestScooter();
        sDTO.setId(maintenance.getScooter_id());
        sDTO.setState("disponible");
        System.out.println(maintenance.getScooter_id());

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<DTORequestScooter> requestEntity = new HttpEntity<>(sDTO, headers);
        String uri = "http://localhost:8003/api/monopatines/finalizar-mantenimiento/paradas/"+maintenance.getScooter_station_id();
        ResponseEntity<String> exchangeResult = restTemplate.exchange(uri, HttpMethod.PUT, requestEntity, String.class);
        System.out.println(exchangeResult);

        Maintenance result = this.maintenanceRepository.save(maintenance);

        return new DTOResponseMaintenance(result);
    }

    @Transactional
    public List<DTOResponseScooter> getReportByKmOptionalPauseTime(boolean pauseBool) {
        String scooter_service_uri = "http://localhost:8003/api/monopatines/";
        return null;
        //return restTemplate.getForObject(scooter_service_uri, String.class);
    }

    @Transactional
    public List<DTOResponseScooter> getReportBy(String reportVariable) {
        String scooter_service_uri = "http://localhost:8003/api/monopatines/";
        return null;
        //return restTemplate.getForObject(scooter_service_uri, String.class);
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
