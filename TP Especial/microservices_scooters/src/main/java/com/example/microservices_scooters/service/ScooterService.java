package com.example.microservices_scooters.service;

import com.example.microservices_scooters.dto.DTORequestScooter;
import com.example.microservices_scooters.dto.DTOResponseScooter;
import com.example.microservices_scooters.entity.Scooter;
import com.example.microservices_scooters.exception.NotFoundException;
import com.example.microservices_scooters.repository.ScooterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class ScooterService {
    
    private final ScooterRepository scooterRepository;
    
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
    public DTOResponseScooter save(DTORequestScooter request ){
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
        //scooter.setEstado(request.getEstado());
        //scooter.setDisponible(request.isDisponible());
        //scooter.setKmsMant(request.getkmsMantenimiento());
        //scooter.setKmsRecorridos(request.getKmsRecorridos());
        //scooter.setTiempoPausado(request.getTiempoPausado());
        //scooter.setTiempoUsoTotal(request.getTiempoUsoTotal());
        //scooter.setUbicacion(request.getUbicacion());
        return this.scooterRepository.save(scooter);
    }

    @Transactional
    public List<DTOResponseScooter> getReport(String ordering) {
        if(ordering.equals("kilometros")){
            return scooterRepository.getReport("kmsTraveled");
        } else if (ordering.equals("tiempo-con-pausa")) {
            return scooterRepository.getReport("totalUsageTime");
        } else if (ordering.equals("tiempo-sin-pausa")) {
            return scooterRepository.getReport("pausedTime");
        } else {
            //return new NotFoundException("ordenamiento invalido: " + ordering);
            return null; // ver que onda se devuelve
        }
    }
}
