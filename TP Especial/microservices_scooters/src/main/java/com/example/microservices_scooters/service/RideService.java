package com.example.microservices_scooters.service;

import com.example.microservices_scooters.dto.DTORequestRide;
import com.example.microservices_scooters.dto.DTOResponseCharged;
import com.example.microservices_scooters.dto.DTOResponseRide;
import com.example.microservices_scooters.dto.DTOResponseScooter;
import com.example.microservices_scooters.entity.Ride;
import com.example.microservices_scooters.exception.NotFoundException;
import com.example.microservices_scooters.repository.RideRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {
    private final RideRepository rideRepository ;

    public RideService(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    @Transactional
    public DTOResponseRide save(DTORequestRide request ){
        Ride ride= new Ride(request);
        Ride result = this.rideRepository.save(ride);
        return new DTOResponseRide(result);
    }

    @Transactional
    public List<DTOResponseRide> findAll(){
        return this.rideRepository.findAll().stream().map( DTOResponseRide::new ).toList();
    }

    @Transactional
    public DTOResponseRide findById( Long id ){
        return this.rideRepository.findById( id )
                .map( DTOResponseRide::new )
                .orElseThrow( () -> new NotFoundException("Cuenta", id ) );
    }

    @Transactional
    public void delete(Long id) {
        this.rideRepository.delete(this.rideRepository.findById(id).orElseThrow(
                () -> new NotFoundException("ID de viaje invalido:" + id)));
    }

    //   chequear
    @Transactional
    public Ride update(Long id, DTORequestRide request) {
        Ride ride = this.rideRepository.findById(id).orElseThrow(() -> new NotFoundException("ID de parada inválido: " + id));

        //station.setLocation(request.getLocation());
        return this.rideRepository.save(ride);
    }

    @Transactional
    public DTOResponseCharged getTotalCharged(int anio, int mesInicio, int mesFin ) {
        return new DTOResponseCharged(this.rideRepository.getTotalCharged(anio,mesInicio,mesFin).get(0));
    }
}
