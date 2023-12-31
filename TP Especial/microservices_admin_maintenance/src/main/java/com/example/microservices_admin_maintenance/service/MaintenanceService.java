package com.example.microservices_admin_maintenance.service;

import com.example.microservices_admin_maintenance.dto.*;
import com.example.microservices_admin_maintenance.entity.Maintenance;
import com.example.microservices_admin_maintenance.exception.NotFoundException;
import com.example.microservices_admin_maintenance.repository.MaintenanceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MaintenanceService {

    private final RestTemplate restTemplate;
    private final MaintenanceRepository maintenanceRepository;

    public MaintenanceService(RestTemplate restTemplate, MaintenanceRepository maintenanceRepository) {
        this.restTemplate = restTemplate;
        this.maintenanceRepository = maintenanceRepository;
    }

    @Transactional
    public List<DTOResponseMaintenance> findAll(HttpHeaders headers) {
        if(checkPermissions(headers,"mantenimiento").is2xxSuccessful()){
            return this.maintenanceRepository
                    .findAll()
                    .stream()
                    .map(this::buildMaintenanceDTO)
                    .toList();
        }
        else throw new NotFoundException("error 500");
    }

    @Transactional
    public DTOResponseMaintenance findById(String id,HttpHeaders headers) {
        if(checkPermissions(headers,"mantenimiento").is2xxSuccessful()){
            return this.maintenanceRepository
                    .findById(id)
                    .map(this::buildMaintenanceDTO)
                    .orElseThrow( () -> new NotFoundException("Maintenance", id));
        }
        else throw new NotFoundException("error 500");
    }

    @Transactional
    public DTOResponseMaintenance save(DTORequestMaintenance request,HttpHeaders headers) {
        if(checkPermissions(headers,"mantenimiento").is2xxSuccessful()){

            DTORequestScooter sDTO = new DTORequestScooter();
            sDTO.setState("en_mantenimiento");

            HttpHeaders headersAux = new HttpHeaders();
            HttpEntity<DTORequestScooter> requestEntity = new HttpEntity<>(sDTO, headersAux);
            String uri = "http://localhost:8003/api/monopatines/"+request.getScooter_id()+"/paradas/"+request.getScooter_station_id();
            ResponseEntity<String> exchangeResult = restTemplate.exchange(uri, HttpMethod.PUT, requestEntity, String.class);

            Maintenance maintenance = new Maintenance(request);
            Maintenance result = this.maintenanceRepository.save(maintenance);

            return new DTOResponseMaintenance(result);
        }
        else throw new NotFoundException("error 500");
    }

    @Transactional
    public void delete(String id,HttpHeaders headers) {
        if(checkPermissions(headers,"mantenimiento").is2xxSuccessful()){
            this.maintenanceRepository.delete(
                    this.maintenanceRepository.findById(id)
                            .orElseThrow( () -> new NotFoundException("Maintenance", id)));
        }
        else throw new NotFoundException("error 500");
    }

    @Transactional
    public DTOResponseMaintenance update(String id, DTORequestMaintenance request, HttpHeaders headers) {
        if(checkPermissions(headers,"mantenimiento").is2xxSuccessful()){
            Maintenance maintenance = this.maintenanceRepository.findById(id).orElseThrow(
                    () -> new NotFoundException("Maintenance", id));

            maintenance.setScooter_id(String.valueOf(request.getScooter_id()));
            maintenance.setScooter_station_id(String.valueOf(request.getScooter_station_id()));
            if (!request.getStart_date().isEmpty())
                maintenance.setStart_date(request.getStart_date());
            if (!request.getEnd_date().isEmpty())
                maintenance.setEnd_date(request.getEnd_date());

            this.maintenanceRepository.save(maintenance);
            return buildMaintenanceDTO(maintenance);
        }
        else throw new NotFoundException("error 500");
    }

    @Transactional
    public DTOResponseMaintenance endScooterMaintenance(String id , HttpHeaders headers) {
        if(checkPermissions(headers,"mantenimiento").is2xxSuccessful()){

            Maintenance maintenance = this.maintenanceRepository.findById(id).orElseThrow(
                    () -> new NotFoundException("Maintenance", id));

            maintenance.setEnd_date(String.valueOf(LocalDateTime.now()));

            DTORequestScooter sDTO = new DTORequestScooter();
            sDTO.setId(Long.valueOf(maintenance.getScooter_id()));
            sDTO.setState("disponible");

            HttpHeaders headersAux = new HttpHeaders();
            HttpEntity<DTORequestScooter> requestEntity = new HttpEntity<>(sDTO, headersAux);
            String uri = "http://localhost:8003/api/monopatines/finalizar-mantenimiento/paradas/"+maintenance.getScooter_station_id();
            ResponseEntity<String> exchangeResult = restTemplate.exchange(uri, HttpMethod.PUT, requestEntity, String.class);

            Maintenance result = this.maintenanceRepository.save(maintenance);

            return new DTOResponseMaintenance(result);
        }
        else throw new NotFoundException("error 500");

    }

    @Transactional
    public DTOResponseReport[] getReportBy(String reportVariable,HttpHeaders headers) {
        if(checkPermissions(headers,"mantenimiento").is2xxSuccessful()){
            String scooter_service_uri = "http://localhost:8003/api/monopatines/reportes/ordenado-por/"+reportVariable;
            return restTemplate.getForEntity(scooter_service_uri, DTOResponseReport[].class).getBody();
        }
        else throw new NotFoundException("error 500");
    }

    @Transactional
    public DTOResponseScootersOfKms[] getReportByKmOptionalPauseTime(String pauseBool,HttpHeaders headers) {
        if(checkPermissions(headers,"mantenimiento").is2xxSuccessful()){
            String scooter_service_uri = "http://localhost:8003/api/monopatines/reporte-monopatines-por-km/con-pausas/"+pauseBool;
            return restTemplate.getForEntity(scooter_service_uri, DTOResponseScootersOfKms[].class).getBody();
        }
        else throw new NotFoundException("error 500");
    }



    private HttpStatusCode checkPermissions(HttpHeaders headers, String rol) {
        HttpHeaders headersAux = headers;
        HttpEntity<DTORequestStatusAccount> requestEntity = new HttpEntity<>(null,headersAux);
        String user_microservice_uri = "http://localhost:8007/api/auth/" + rol;
        return this.restTemplate.exchange(user_microservice_uri, HttpMethod.GET, requestEntity, String.class).getStatusCode();
    }


    private DTOResponseMaintenance buildMaintenanceDTO(Maintenance m) {
        return new DTOResponseMaintenance(
                m.getId(),
                Long.valueOf(m.getScooter_id()),
                Long.valueOf(m.getScooter_station_id()),
                String.valueOf(m.getStart_date()),
                String.valueOf(m.getEnd_date())
        );
    }

}
