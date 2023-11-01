package com.example.microservices_scooters.service;

import com.example.microservices_scooters.dto.DTORequestScooter;
import com.example.microservices_scooters.dto.DTORequestStation;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StationService {
    private final StationRepository stationRepository ;
    private final ScooterRepository scooterRepository ;
    @Transactional
    public DTOResponseStation save(DTORequestStation request ){
        Station station= new Station(request);
        Station result = this.stationRepository.save(station);
        return new DTOResponseStation(result);
    }



    @Transactional
    public List<DTOResponseStation> findAll(){
        return this.stationRepository.findAll().stream().map( DTOResponseStation::new ).toList();
    }

    @Transactional
    public DTOResponseStation findById( Long id ){
        return this.stationRepository.findById( id )
                .map( DTOResponseStation::new )
                .orElseThrow( () -> new NotFoundException("Cuenta", id ) );
    }

    @Transactional
    public void delete(Long id) {
        this.stationRepository.delete(this.stationRepository.findById(id).orElseThrow(
                () -> new NotFoundException("ID de parada invalido:" + id)));
    }

    //   chequear
    @Transactional
    public Station update(Long id, DTORequestStation request) {
        Station station = this.stationRepository.findById(id).orElseThrow(() -> new NotFoundException("ID de parada inválido: " + id));
        station.setName(request.getName());
        station.setLocation(request.getLocation());
        return this.stationRepository.save(station);
    }
    @Transactional
    public Station addScooterToStation(Long id, Long idScooter) {
        Scooter scooter = this.scooterRepository.findById(idScooter).orElseThrow(() -> new NotFoundException("ID de monopatín inválido: " + id));
        Station station = this.stationRepository.findById(id).orElseThrow(() -> new NotFoundException("ID de parada inválido: " + id));
        if (station.getCantMaxSkateboards() <= station.getSkateboards().size()){
            throw new NotFoundException("La estación no puede agregar un monopatín. Está llena");
        }
        else{
            if (!station.addScooterToStation(scooter)){
                throw new NotFoundException("El monopatín que quiere agregar no se encuentra en la misma ubicación que la estación");
            }
            else{
                return this.stationRepository.save(station);
            }
        }
    }

}