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

    public Station addScooterToStation(Long id, Long idScooter) {
        Scooter scooter = this.scooterRepository.findById(idScooter).orElseThrow(() -> new NotFoundException("ID de monopatín inválido: " + id));
        Station station = this.stationRepository.findById(id).orElseThrow(() -> new NotFoundException("ID de parada inválido: " + id));
        if (station.getCantMaxSkateboards() >= station.getSkateboards().size()){
            return null; // ver que onda se devuelve
        }
        else{
            if (!station.addScooterToStation(scooter)){
                return null;  // ver que onda se devuelve
            }
            else{
                //realizarSolicitudPOST("{id_station:"+id+" , id_scooter:"+idScooter+"}");
                return this.stationRepository.save(station);
            }

        }
    }
    private String realizarSolicitudPOST(String body) {
        String url = "//localhost:3306/mantenimientos"; // Cambia la URL según tu entorno
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        return response.getBody();
    }

}