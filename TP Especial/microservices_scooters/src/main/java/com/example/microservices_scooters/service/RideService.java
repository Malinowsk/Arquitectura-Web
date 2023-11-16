package com.example.microservices_scooters.service;

import com.example.microservices_scooters.dto.*;
import com.example.microservices_scooters.entity.Ride;
import com.example.microservices_scooters.entity.Scooter;
import com.example.microservices_scooters.entity.Station;
import com.example.microservices_scooters.exception.NotFoundException;
import com.example.microservices_scooters.repository.RideRepository;
import com.example.microservices_scooters.repository.StationRepository;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.List;

@Service
public class RideService {
    private final RideRepository rideRepository ;
    private final StationRepository stationRepository ;
    private final RestTemplate restTemplate;


    public RideService(RideRepository rideRepository, StationRepository stationRepository, RestTemplate restTemplate) {
        this.rideRepository = rideRepository;
        this.stationRepository = stationRepository;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public List<DTOResponseRide> findAll(HttpHeaders headers){
        if(checkPermissions(headers).is2xxSuccessful()){
            return this.rideRepository.findAll().stream().map( DTOResponseRide::new ).toList();
        }
        else throw new NotFoundException("error 500");
    }

    @Transactional
    public DTOResponseRide findById( Long id ,HttpHeaders headers){
        if(checkPermissions(headers).is2xxSuccessful()){
            return this.rideRepository.findById( id )
                    .map( DTOResponseRide::new )
                    .orElseThrow( () -> new NotFoundException("Cuenta", id ) );
        }
        else throw new NotFoundException("error 500");
    }

    @Transactional
    public DTOResponseRide save(DTORequestRide request ,HttpHeaders headers){
        if(checkPermissions(headers).is2xxSuccessful()){
            Station station = this.stationRepository.findById(request.getFinalStation()).orElseThrow(() -> new NotFoundException("ID de parada inválido: " + request.getFinalStation()));
            Ride ride= new Ride(request);
            ride.setFinalStation(station);
            Ride result = this.rideRepository.save(ride);
            return new DTOResponseRide(result);
        }
        else throw new NotFoundException("error 500");
    }

    @Transactional
    public void delete(Long id,HttpHeaders headers) {
        if(checkPermissions(headers).is2xxSuccessful()){
            this.rideRepository.delete(this.rideRepository.findById(id).orElseThrow(
                    () -> new NotFoundException("ID de viaje invalido:" + id)));
        }
        else throw new NotFoundException("error 500");
    }

    @Transactional
    public Ride update(Long id, DTORequestRide request,HttpHeaders headers) {
        if(checkPermissions(headers).is2xxSuccessful()){
            Ride ride = this.rideRepository.findById(id).orElseThrow(() -> new NotFoundException("ID de parada inválido: " + id));
            ride.setScooter(request.getScooter());
            ride.setIdUser(request.getIdUser());
            ride.setIdAccount(request.getIdAccount());
            ride.setInitiated(request.getInitiated());
            ride.setFinalized(request.getFinalized());
            ride.setKilometersTraveled(request.getKilometersTraveled());
            ride.setTotalPrice(request.getTotalPrice());
            ride.setPauseTime(request.getPauseTime());
            ride.setActivePause(request.isActivePause());
            ride.setId_tarifa(request.getId_tarifa());
            Station station = this.stationRepository.findById(request.getFinalStation()).orElseThrow(() -> new NotFoundException("ID de parada inválido: " + request.getFinalStation()));
            ride.setFinalStation(station);
            return this.rideRepository.save(ride);
        }
        else throw new NotFoundException("error 500");
    }

    @Transactional
    public DTOResponseCharged getTotalCharged(int anio, int mesInicio, int mesFin ) {
        return new DTOResponseCharged(this.rideRepository.getTotalCharged(anio,mesInicio,mesFin).get(0));
    }

    private HttpStatusCode checkPermissions(HttpHeaders headers) {
        HttpHeaders headersAux = headers;
        HttpEntity<DTORequestRide> requestEntity = new HttpEntity<>(null,headersAux);
        String user_microservice_uri = "http://localhost:8007/api/auth/admin";
        return this.restTemplate.exchange(user_microservice_uri, HttpMethod.GET, requestEntity, String.class).getStatusCode();
    }

}
