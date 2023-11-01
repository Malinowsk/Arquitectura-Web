package com.example.microservices_scooters.service;

import com.example.microservices_scooters.dto.DTORequestScooter;
import com.example.microservices_scooters.dto.DTORespondeStatusQualityScooter;
import com.example.microservices_scooters.dto.DTOResponseScooter;
import com.example.microservices_scooters.entity.Scooter;
import com.example.microservices_scooters.entity.Station;
import com.example.microservices_scooters.exception.NotFoundException;
import com.example.microservices_scooters.repository.ScooterRepository;
import com.example.microservices_scooters.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class ScooterService {
    
    private final ScooterRepository scooterRepository;
    private final StationRepository stationRepository;
    
    @Transactional
    public List<DTOResponseScooter> findAll(){
        return this.scooterRepository.findAll().stream().map( DTOResponseScooter::new ).toList();
    }

    @Transactional
    public DTOResponseScooter findById( Long id ){
        return this.scooterRepository.findById( id )
                .map( DTOResponseScooter::new )
                .orElseThrow( () -> new NotFoundException("scooter", id ) );
    }

    @Transactional
    public DTOResponseScooter save(DTORequestScooter request){
        Scooter scooter = new Scooter(request);
        Scooter result = this.scooterRepository.save(scooter);
        return new DTOResponseScooter(result);
    }

    @Transactional
    public void delete(Long id) {
        this.scooterRepository.delete(this.scooterRepository.findById(id).orElseThrow(
                () -> new NotFoundException("ID de scooter invalido:" + id)));
    }

    @Transactional
    public Scooter update(Long id, DTORequestScooter request) {
        Scooter scooter = this.scooterRepository.findById(id).orElseThrow(
                () -> new NotFoundException("ID de monopatín inválido: " + id));

        scooter.setNumberOfTrips(request.getNumberOfTrips());
        scooter.setState(request.getState());
        scooter.setKmsMant(request.getKmsMant());
        scooter.setKmsTraveled(request.getKmsTraveled());
        scooter.setPausedTime(request.getPausedTime());
        scooter.setTotalUsageTime(request.getTotalUsageTime());
        scooter.setLocation(request.getLocation());
        return this.scooterRepository.save(scooter);
    }

    @Transactional
    public List<DTOResponseScooter> getReport(String ordering) {
        if(ordering.equals("kilometros")||ordering.equals("tiempo-con-pausa")){
            return scooterRepository.getReport(ordering);
        } else if (ordering.equals("tiempo-sin-pausa")) {
            return scooterRepository.getReport(ordering);
        } else {
            //return new NotFoundException("ordenamiento invalido: " + ordering);
            return null; // ver que onda se devuelve
        }
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
        return this.scooterRepository.getQuantityBasedOnStatus("en_uso","mantenimiento");
    }
    public List<DTOResponseScooter> getScootersSurroundings(Long id) {
        Scooter scooter = this.scooterRepository.findById(id).orElseThrow(
                () -> new NotFoundException("ID de monopatín inválido: " + id));
        return this.scooterRepository.getScootersSurroundings(scooter.getLocation().getLongitud(),scooter.getLocation().getLatitud()).stream().map(DTOResponseScooter::new).toList();
    }

}
