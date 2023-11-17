package com.example.microservices_scooters.service;

import com.example.microservices_scooters.dto.DTORequestScooter;
import com.example.microservices_scooters.dto.DTORequestStation;
import com.example.microservices_scooters.dto.DTOResponseScooter;
import com.example.microservices_scooters.dto.DTOResponseStation;
import com.example.microservices_scooters.entity.Scooter;
import com.example.microservices_scooters.entity.Station;
import com.example.microservices_scooters.exception.NotFoundException;
import com.example.microservices_scooters.repository.ScooterRepository;
import com.example.microservices_scooters.repository.StationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StationService {
    private final StationRepository stationRepository ;
    private final ScooterRepository scooterRepository ;
    private final RestTemplate restTemplate;

    public StationService(StationRepository stationRepository, ScooterRepository scooterRepository, RestTemplate restTemplate) {
        this.stationRepository = stationRepository;
        this.scooterRepository = scooterRepository;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public List<DTOResponseStation> findAll(HttpHeaders headers){
        if(checkPermissions(headers).is2xxSuccessful()){
            return this.stationRepository.findAll().stream().map( DTOResponseStation::new ).toList();
        }
        else throw new NotFoundException("error 500");
    }

    @Transactional
    public DTOResponseStation findById( Long id,HttpHeaders headers ){
        if(checkPermissions(headers).is2xxSuccessful()){
            return this.stationRepository.findById( id )
                    .map( DTOResponseStation::new )
                    .orElseThrow( () -> new NotFoundException("Cuenta", id ) );
        }
        else throw new NotFoundException("error 500");
    }

    @Transactional
    public DTOResponseStation save(DTORequestStation request,HttpHeaders headers ){
        if(checkPermissions(headers).is2xxSuccessful()){
            Station station= new Station(request);
            Station result = this.stationRepository.save(station);
            return new DTOResponseStation(result);
        }
        else throw new NotFoundException("error 500");
    }


    @Transactional
    public void delete(Long id,HttpHeaders headers) {
        if(checkPermissions(headers).is2xxSuccessful()){
            this.stationRepository.delete(this.stationRepository.findById(id).orElseThrow(
                    () -> new NotFoundException("ID de parada invalido:" + id)));
        }
        else throw new NotFoundException("error 500");

    }

    //   chequear
    @Transactional
    public Station update(Long id, DTORequestStation request,HttpHeaders headers) {
        if(checkPermissions(headers).is2xxSuccessful()){
            Station station = this.stationRepository.findById(id).orElseThrow(() -> new NotFoundException("ID de parada inválido: " + id));
            station.setName(request.getName());
            station.setLocation(request.getLocation());
            station.setCantMaxSkateboards(request.getCantMaxSkateboards());
            return this.stationRepository.save(station);
        }
        else throw new NotFoundException("error 500");

    }

    @Transactional
    public Station addScooterToStation(Long id, Long idScooter) {
        Scooter scooter = this.scooterRepository.findById(idScooter).orElseThrow(() -> new NotFoundException("ID de monopatín inválido: " + idScooter));
        Station station = this.stationRepository.findById(id).orElseThrow(() -> new NotFoundException("ID de parada inválido: " + id));
        if (station.getCantMaxSkateboards() <= station.getSkateboards().size()){
            throw new NotFoundException("La estación no puede agregar un monopatín. Está llena");
        }
        else{
            if (!station.addScooterToStation(scooter)){
                throw new NotFoundException("El monopatín que quiere agregar no se encuentra en la misma ubicación que la estación");
            }
            else{
                scooter.setState("disponible");
                this.scooterRepository.save(scooter);
                return this.stationRepository.save(station);
            }
        }
    }

    private HttpStatusCode checkPermissions(HttpHeaders headers) {
        HttpHeaders headersAux = headers;
        HttpEntity<DTORequestScooter> requestEntity = new HttpEntity<>(null,headersAux);
        String user_microservice_uri = "http://localhost:8007/api/auth/admin";
        return this.restTemplate.exchange(user_microservice_uri, HttpMethod.GET, requestEntity, String.class).getStatusCode();
    }

}