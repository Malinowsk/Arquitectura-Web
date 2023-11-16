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
                .findById(String.valueOf(id))
                .map(this::buildMaintenanceDTO)
                .orElseThrow( () -> new NotFoundException("Maintenance", id));
    }

    @Transactional
    public DTOResponseMaintenance save(DTORequestMaintenance request,HttpHeaders headers) {
        if(checkPermissions(headers,"mantenimiento").is2xxSuccessful()){
            Maintenance maintenance = new Maintenance(request);
            Maintenance result = this.maintenanceRepository.save(maintenance);

            DTORequestScooter sDTO = new DTORequestScooter();
            sDTO.setState("en_mantenimiento");

            HttpHeaders headersAux = new HttpHeaders();
            HttpEntity<DTORequestScooter> requestEntity = new HttpEntity<>(sDTO, headersAux);
            String uri = "http://localhost:8003/api/monopatines/"+request.getScooter_id()+"/paradas/"+request.getScooter_station_id();
            ResponseEntity<String> exchangeResult = restTemplate.exchange(uri, HttpMethod.PUT, requestEntity, String.class);

            return new DTOResponseMaintenance(result);
        }
        else throw new NotFoundException("error 500");
    }

    @Transactional
    public void delete(Long id) {
        this.maintenanceRepository.delete(
                this.maintenanceRepository.findById(String.valueOf(id))
                        .orElseThrow( () -> new NotFoundException("Maintenance", id)));
    }

    @Transactional
    public DTOResponseMaintenance update(Long id, DTORequestMaintenance request) {
        Maintenance maintenance = this.maintenanceRepository.findById(String.valueOf(id)).orElseThrow(
                () -> new NotFoundException("Maintenance", id));

        //Falta chequear que no vengan datos null
        maintenance.setScooter_id(String.valueOf(request.getScooter_id()));
        maintenance.setScooter_station_id(String.valueOf(request.getScooter_station_id()));
        maintenance.setStart_date(request.getStart_date());
        maintenance.setEnd_date(request.getEnd_date());

        this.maintenanceRepository.save(maintenance);
        return buildMaintenanceDTO(maintenance);
    }

    @Transactional
    public DTOResponseMaintenance endScooterMaintenance(Long id) {
        Maintenance maintenance = this.maintenanceRepository.findById(String.valueOf(id)).orElseThrow(
                () -> new NotFoundException("Maintenance", id));

        maintenance.setEnd_date(String.valueOf(LocalDateTime.now()));

        DTORequestScooter sDTO = new DTORequestScooter();
        sDTO.setId(Long.valueOf(maintenance.getScooter_id()));
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
    public DTOResponseReport[] getReportBy(String reportVariable) {
        String scooter_service_uri = "http://localhost:8003/api/monopatines/reportes/ordenado-por/"+reportVariable;
        return restTemplate.getForEntity(scooter_service_uri, DTOResponseReport[].class).getBody();
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
