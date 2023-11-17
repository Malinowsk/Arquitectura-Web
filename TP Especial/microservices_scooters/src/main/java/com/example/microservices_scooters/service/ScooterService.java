package com.example.microservices_scooters.service;

import com.example.microservices_scooters.dto.*;
import com.example.microservices_scooters.entity.Scooter;
import com.example.microservices_scooters.entity.Station;
import com.example.microservices_scooters.exception.NotFoundException;
import com.example.microservices_scooters.repository.ScooterRepository;
import com.example.microservices_scooters.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class ScooterService {
    private final RestTemplate restTemplate;
    private final ScooterRepository scooterRepository;
    private final StationRepository stationRepository;

    public ScooterService(RestTemplate restTemplate, ScooterRepository scooterRepository, StationRepository stationRepository) {
        this.restTemplate = restTemplate;
        this.scooterRepository = scooterRepository;
        this.stationRepository = stationRepository;
    }

    @Transactional
    public List<DTOResponseScooter> findAll(HttpHeaders headers){
        if(checkPermissions(headers).is2xxSuccessful()){
            return this.scooterRepository.findAll().stream().map( DTOResponseScooter::new ).toList();
        }
        else throw new NotFoundException("error 500");
    }

    @Transactional
    public DTOResponseScooter findById( Long id,HttpHeaders headers ){
        if(checkPermissions(headers).is2xxSuccessful()){
            return this.scooterRepository.findById( id )
                    .map( DTOResponseScooter::new )
                    .orElseThrow( () -> new NotFoundException("scooter", id ) );
        }
        else throw new NotFoundException("error 500");

    }

    @Transactional
    public DTOResponseScooter save(DTORequestScooter request,HttpHeaders headers){
        if(checkPermissions(headers).is2xxSuccessful()){
            Scooter scooter = new Scooter(request);
            Scooter result = this.scooterRepository.save(scooter);
            return new DTOResponseScooter(result);
        }
        else throw new NotFoundException("error 500");

    }

    @Transactional
    public void delete(Long id,HttpHeaders headers) {
        if(checkPermissions(headers).is2xxSuccessful()){
            this.scooterRepository.delete(this.scooterRepository.findById(id).orElseThrow(
                    () -> new NotFoundException("ID de scooter invalido:" + id)));
        }
        else throw new NotFoundException("error 500");
    }

    @Transactional
    public Scooter update(Long id, DTORequestScooter request,HttpHeaders headers) {
        if(checkPermissions(headers).is2xxSuccessful()){
            Scooter scooter = this.scooterRepository.findById(id).orElseThrow(
                    () -> new NotFoundException("ID de monopatín inválido: " + id));

            if (request.getState() != null)
                scooter.setState(request.getState());
            if (request.getKmsMant() != 0)
                scooter.setKmsMant(request.getKmsMant());
            if (request.getKmsTraveled() != 0)
                scooter.setKmsTraveled(request.getKmsTraveled());
            if (request.getPausedTime() != 0)
                scooter.setPausedTime(request.getPausedTime());
            if (request.getTotalUsageTime() != 0)
                scooter.setTotalUsageTime(request.getTotalUsageTime());
            if (request.getLocation() != null)
                scooter.setLocation(request.getLocation());
            if (request.getModel() != null)
                scooter.setModel(request.getModel());

            return this.scooterRepository.save(scooter);
        }
        else throw new NotFoundException("error 500");
    }

    @Transactional
    public List<DTOResponseReport> getReport(String ordering) {
        if(ordering.equals("kilometros")||ordering.equals("tiempo-con-pausa")||ordering.equals("tiempo-sin-pausa")){
            return this.scooterRepository
                    .getReport(ordering)
                    .stream()
                    .map(obj -> new DTOResponseReport((long) obj[0], (String) obj[1], (double)obj[2]))
                    .toList();
        } else {
            throw new NotFoundException("El tipo de odenamiento es invalido");
        }
    }

    @Transactional
    public List<DTOResponseScootersOfKms> getScootersOfKms(String with_pause) {
        return this.scooterRepository
                .getScootersOfKms(Boolean.parseBoolean(with_pause))
                .stream()
                .map(obj -> new DTOResponseScootersOfKms((long) obj[0], (String) obj[1], (double)obj[2], (long)obj[3]))
                .toList();
    }

    @Transactional
    public Scooter maintenance(Long id_station, Long id, DTORequestScooter request) {
        Scooter scooter = this.scooterRepository.findById(id).orElseThrow(
                () -> new NotFoundException("ID de monopatín inválido: " + id));
        Station station = this.stationRepository.findById(id_station).orElseThrow(
                () -> new NotFoundException("ID de parada inválido: " + id_station));
        scooter.setState(request.getState());
        if (!station.removeScooterToStation(scooter)){
            throw new NotFoundException("El monopatín que quiere elimina no se encuentra en la estación");
        }
        else{
            this.stationRepository.save(station);
            return this.scooterRepository.save(scooter);
        }
    }

    @Transactional
    public Scooter endMaintenance(Long id_station, DTORequestScooter request) {
        Scooter scooter = this.scooterRepository.findById(request.getId()).orElseThrow(
                () -> new NotFoundException("ID de monopatín inválido: " + request.getId()));
        Station station = this.stationRepository.findById(id_station).orElseThrow(
                () -> new NotFoundException("ID de parada inválido: " + id_station));
        scooter.setState(request.getState());
        if (!station.addScooterToStation(scooter)){
            throw new NotFoundException("El monopatín que quiere agregar tiene una ubicacion diferente a la estación");
        }
        else{
            this.stationRepository.save(station);
            return this.scooterRepository.save(scooter);
        }
    }

    @Transactional
    public List<DTOResponseScooter> getBySearch( int cant, int anio ) {
        return this.scooterRepository.getBySearch(cant, anio).stream().map(DTOResponseScooter::new).toList();
    }

    @Transactional
    public DTORespondeStatusQualityScooter getQuantityBasedOnStatus() {
        return this.scooterRepository.getQuantityBasedOnStatus("en_uso","en_mantenimiento");
    }
    public List<DTOResponseScooter> getScootersSurroundings(Long id) {
        Scooter scooter = this.scooterRepository.findById(id).orElseThrow(
                () -> new NotFoundException("ID de monopatín inválido: " + id));
        return this.scooterRepository
                .getScootersSurroundings(scooter.getLocation().getLongitud(),scooter.getLocation().getLatitud())
                .stream()
                .map(DTOResponseScooter::new)
                .toList();
    }

    private HttpStatusCode checkPermissions(HttpHeaders headers) {
        HttpHeaders headersAux = headers;
        HttpEntity<DTORequestScooter> requestEntity = new HttpEntity<>(null,headersAux);
        String user_microservice_uri = "http://localhost:8007/api/auth/admin";
        return this.restTemplate.exchange(user_microservice_uri, HttpMethod.GET, requestEntity, String.class).getStatusCode();
    }


}
