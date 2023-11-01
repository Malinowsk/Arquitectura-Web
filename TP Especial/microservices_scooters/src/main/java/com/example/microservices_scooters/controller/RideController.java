package com.example.microservices_scooters.controller;

import com.example.microservices_scooters.dto.*;
import com.example.microservices_scooters.entity.Ride;
import com.example.microservices_scooters.service.RideService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/viajes")
@RequiredArgsConstructor
public class RideController {

    @Autowired
    private RideService rideService;

    @GetMapping("")
    public List<DTOResponseRide> findAll(){
        return this.rideService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(rideService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Viaje inexistente");
        }

    }
    @PostMapping("")
    public ResponseEntity<?> save( @RequestBody @Validated DTORequestRide request ){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(rideService.save(request));
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Ocurrio un error, revise los campos ingresados");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            this.rideService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente el viaje con el id: " + id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No se pudo eliminar el viaje con id: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Validated DTORequestRide request) {
        try {
            Ride ride = rideService.update(id, request);
            DTOResponseRide response = new DTOResponseRide(ride);

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el viaje con el ID proporcionado.");
        }
    }

    @GetMapping("/facturado/anio/{anio}/mes-desde/{mes_inicio}/mes-hasta/{mes_fin}")
    public ResponseEntity<?> getTotalCharged(@PathVariable int anio, @PathVariable int mes_inicio, @PathVariable int mes_fin){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(rideService.getTotalCharged(anio,mes_inicio,mes_fin));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No se pudo calcular lo facturado");
        }

    }

}
