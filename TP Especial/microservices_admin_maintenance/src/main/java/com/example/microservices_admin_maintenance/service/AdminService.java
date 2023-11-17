package com.example.microservices_admin_maintenance.service;

import com.example.microservices_admin_maintenance.dto.*;
import com.example.microservices_admin_maintenance.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AdminService {

    private final RestTemplate restTemplate;

    public AdminService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    //Ubicar monopatín en parada (opcional)
    @Transactional
    public DTOResponseScooter assignScooterToStation(Long station_id, DTORequestScooter scooterDTO,HttpHeaders headers) {
        if(checkPermissions(headers).is2xxSuccessful()){
            HttpHeaders auxHeaders = new HttpHeaders();
            HttpEntity<DTORequestScooter> requestEntity = new HttpEntity<>(scooterDTO, auxHeaders);
            String station_microservice_uri = "http://localhost:8003/api/paradas/"+station_id+"/monopatines";
            return this.restTemplate.exchange(station_microservice_uri, HttpMethod.PUT, requestEntity, DTOResponseScooter.class).getBody();
        }
        else throw new NotFoundException("error 500");

    }


    @Transactional
    public DTOResponseAccount changeAccountStatus(Long accID, DTORequestStatusAccount accDTO, HttpHeaders headers) {
        if(checkPermissions(headers).is2xxSuccessful()){
            System.out.println("sdfsd");
            HttpHeaders auxHeaders = new HttpHeaders();
            HttpEntity<DTORequestStatusAccount> requestEntity = new HttpEntity<>(accDTO, auxHeaders);
            String user_microservice_uri = "http://localhost:8007/api/accounts/"+accID+"/status";
            ResponseEntity<DTOResponseAccount> response = this.restTemplate.exchange(user_microservice_uri, HttpMethod.PUT, requestEntity, DTOResponseAccount.class);
            return response.getBody();
        }
        else throw new NotFoundException("error 500");

    }

    @Transactional
    public List scooterReportByAmountOfTripsAndYear(int trip_qty, int year, HttpHeaders headers) {
        if(checkPermissions(headers).is2xxSuccessful()){
            String scooter_microservice_uri = "http://localhost:8003/api/monopatines/cantidad-viajes/"+trip_qty+"/anio/"+year;
            return restTemplate.getForObject(scooter_microservice_uri, List.class);
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
