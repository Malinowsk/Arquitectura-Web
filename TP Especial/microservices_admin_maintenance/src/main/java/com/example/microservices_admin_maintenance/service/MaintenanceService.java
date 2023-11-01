package com.example.microservices_admin_maintenance.service;

import com.example.microservices_admin_maintenance.dto.DTORequestScooterModel;
import com.example.microservices_admin_maintenance.entity.Maintenance;
import com.example.microservices_admin_maintenance.exception.NotFoundException;
import com.example.microservices_admin_maintenance.repository.MaintenanceRepository;
import com.example.microservices_admin_maintenance.dto.DTORequestMaintenance;
import com.example.microservices_admin_maintenance.dto.DTOResponseMaintenance;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
        //Asumo que me mandan un ID de monopatin correcto o chequeo con el servicio de monopatines?
        Maintenance maintenance = new Maintenance(request);
        Maintenance result = this.maintenanceRepository.save(maintenance);

        HttpHeaders headers = new HttpHeaders();
        String newStatus = "en mantenimiento";
        //TODO: Revisar, o bien mando un DTORequestScooter con el nuevo estado o hago otra cosa para actualizar el estado del monopatin
        //      en el otro microservicio
        HttpEntity<String> requestEntity = new HttpEntity<>(newStatus, headers);
        String uri = "http://localhost:8003/api/monopatines/"+request.getScooter_id();
        restTemplate.exchange(uri, HttpMethod.POST, requestEntity,String.class);

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
    public String changeScooterStatus(Long id) {
        HttpHeaders headers = new HttpHeaders();
        String newStatus = "en mantenimiento";
        HttpEntity<String> requestEntity = new HttpEntity<>(newStatus, headers);
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
