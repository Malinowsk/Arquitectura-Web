package com.example.microservices_admin_maintenance.service;

import com.example.microservices_admin_maintenance.dto.*;
import com.example.microservices_admin_maintenance.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AdminService {

    private final RestTemplate restTemplate;

    public AdminService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Agregar monopatín
    @Transactional
    public String createScooter(DTORequestScooterModel scooterModel,HttpHeaders headers) {
        if(checkPermissions(headers).is2xxSuccessful()){
            HttpHeaders auxHeaders = new HttpHeaders();
            HttpEntity<DTORequestScooterModel> requestEntity = new HttpEntity<>(scooterModel, auxHeaders);
            String scooter_microservice_uri = "http://localhost:8003/api/monopatines";
            return this.restTemplate.exchange(scooter_microservice_uri, HttpMethod.POST, requestEntity, String.class).getBody();
        }
        else throw new NotFoundException("error 500");
    }

    //Ubicar monopatín en parada (opcional)
    @Transactional
    public String assignScooterToStation(Long station_id, DTORequestScooter scooterDTO,HttpHeaders headers) {
        if(checkPermissions(headers).is2xxSuccessful()){
            HttpHeaders auxHeaders = new HttpHeaders();
            HttpEntity<DTORequestScooter> requestEntity = new HttpEntity<>(scooterDTO, auxHeaders);
            String station_microservice_uri = "http://localhost:8003/api/paradas/"+station_id+"/scooters";
            return this.restTemplate.exchange(station_microservice_uri, HttpMethod.PUT, requestEntity, String.class).getBody();
        }
        else throw new NotFoundException("error 500");

    }



    @Transactional
    public String changeAccountStatus(Long accID, DTORequestStatusAccount accDTO,HttpHeaders headers) {
        if(checkPermissions(headers).is2xxSuccessful()){
            HttpHeaders auxHeaders = new HttpHeaders();
            HttpEntity<DTORequestStatusAccount> requestEntity = new HttpEntity<>(accDTO, auxHeaders);
            String user_microservice_uri = "http://localhost:8007/api/accounts/"+accID+"/status";
            return this.restTemplate.exchange(user_microservice_uri, HttpMethod.PUT, requestEntity, String.class).getBody();
        }
        else throw new NotFoundException("error 500");

    }

    @Transactional
    public String scooterReportByAmountOfTripsAndYear(int trip_qty, int year,HttpHeaders headers) {
        if(checkPermissions(headers).is2xxSuccessful()){
            String scooter_microservice_uri = "http://localhost:8003/api/monopatines/cantidad-viajes/"+trip_qty+"/anio/"+year;
            return restTemplate.getForObject(scooter_microservice_uri, String.class);
        }
        else throw new NotFoundException("error 500");
    }

    @Transactional
    public String amountEarnedInTimePeriod(int year, int starting_month, int ending_month,HttpHeaders headers) {
        if(checkPermissions(headers).is2xxSuccessful()){
            String scooter_microservice_uri = "http://localhost:8003/api/viajes/facturado/anio/"+year+"/mes-desde/"+starting_month+"/mes-hasta/"+ending_month;
            return restTemplate.getForObject(scooter_microservice_uri, String.class);
        }
        else throw new NotFoundException("error 500");
    }

    @Transactional
    public String quantityOfScootersInOperation(HttpHeaders headers) {
        if(checkPermissions(headers).is2xxSuccessful()){
            String scooter_microservice_uri = "http://localhost:8003/api/monopatines/operacion-vs-mantenimiento";
            return restTemplate.getForObject(scooter_microservice_uri, String.class);
        }
        else throw new NotFoundException("error 500");
    }

    private HttpStatusCode checkPermissions(HttpHeaders headers) {
        HttpHeaders headersAux = headers;
        HttpEntity<DTORequestStatusAccount> requestEntity = new HttpEntity<>(null,headersAux);
        String user_microservice_uri = "http://localhost:8007/api/auth/admin";
        return this.restTemplate.exchange(user_microservice_uri, HttpMethod.GET, requestEntity, String.class).getStatusCode();
    }

}
