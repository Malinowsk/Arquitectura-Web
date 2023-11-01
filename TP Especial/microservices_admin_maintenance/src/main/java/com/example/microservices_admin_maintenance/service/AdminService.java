package com.example.microservices_admin_maintenance.service;

import com.example.microservices_admin_maintenance.dto.*;
import com.example.microservices_admin_maintenance.entity.Fare;
import com.example.microservices_admin_maintenance.repository.FareRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final RestTemplate restTemplate;

    private final FareRepository fareRepository;

    @Transactional
    public String createScooter(DTORequestScooterModel scooterModel) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<DTORequestScooterModel> requestEntity = new HttpEntity<>(scooterModel, headers);
        String scooter_microservice_uri = "http://localhost:8003/api/monopatines";
        return this.restTemplate.exchange(scooter_microservice_uri, HttpMethod.POST, requestEntity, String.class).getBody();
    }

    @Transactional
    public String assignScooterToStation(Long station_id, DTORequestScooter scooterDTO) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<DTORequestScooter> requestEntity = new HttpEntity<>(scooterDTO, headers);
        String station_microservice_uri = "http://localhost:8003/api/paradas/"+station_id+"/scooters";
        return this.restTemplate.exchange(station_microservice_uri, HttpMethod.PUT, requestEntity, String.class).getBody();
    }

    @Transactional
    public DTOFareResponse addFare(DTOFareRequest fDTO) {
        Fare fare = new Fare(fDTO);
        Fare result = fareRepository.save(fare);
        return new DTOFareResponse(result);
    }

    /*
    @Transactional
    public DTOFareResponse updateFare(Long id, DTOFareRequest fDTO) {
        Fare fare = this.fareRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Maintenance", id));

        //Falta chequear que no vengan datos null
        fare.setName(fDTO.getName());
        fare.setCost(fDTO.getCost());
        fare.setExtended_pause_cost(fDTO.getExtended_pause_cost());

        Fare result = fareRepository.save(fare);
        return new DTOFareResponse(result);
    }

    @Transactional
    public void deleteFare(Long id) {
        this.fareRepository.delete(
                this.fareRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Maintenance", id)));
    }*/

    @Transactional
    public String changeAccountStatus(Long accID, DTORequestStatusAccount accDTO) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<DTORequestStatusAccount> requestEntity = new HttpEntity<>(accDTO, headers);
        String user_microservice_uri = "http://localhost:8007/api/accounts/"+accID+"/status";
        return this.restTemplate.exchange(user_microservice_uri, HttpMethod.PUT, requestEntity, String.class).getBody();
    }

    @Transactional
    public String scooterReportByAmountOfTripsAndYear(int trip_qty, int year) {
        String scooter_microservice_uri = "http://localhost:8003/api/monopatines/cantidad-viajes/"+trip_qty+"/anio/"+year;
        return restTemplate.getForObject(scooter_microservice_uri, String.class);
    }

    @Transactional
    public String amountEarnedInTimePeriod(int year, int starting_month, int ending_month) {
        String scooter_microservice_uri = "http://localhost:8003/api/viajes/facturado/anio/"+year+"/mes-desde/"+starting_month+"/mes-hasta/"+ending_month;
        return restTemplate.getForObject(scooter_microservice_uri, String.class);
    }

    @Transactional
    public String quantityOfScootersInOperation() {
        String scooter_microservice_uri = "http://localhost:8003/api/monopatines/operacion-vs-mantenimiento";
        return restTemplate.getForObject(scooter_microservice_uri, String.class);
    }


}
