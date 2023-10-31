package com.example.microservices_scooters.controller;

import com.example.microservices_scooters.dto.DTORequestStation;
import com.example.microservices_scooters.dto.DTOResponseScooter;
import com.example.microservices_scooters.dto.DTOResponseStation;
import com.example.microservices_scooters.entity.Station;
import com.example.microservices_scooters.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@RestController
@RequestMapping("api/paradas")
@RequiredArgsConstructor
public class StationController {

    @Autowired
    private StationService stationService;

    @GetMapping("")
    public List<DTOResponseStation> findAll(){
        return this.stationService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(stationService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Parada inexistente");
        }

    }
    @PostMapping("")
    public ResponseEntity<?> save( @RequestBody @Validated DTORequestStation request ){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(stationService.save(request));
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Ocurrio un error, revise los campos ingresados");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            this.stationService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente la parada con el id: " + id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No se pudo eliminar la parada con id: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Validated DTORequestStation request) {
        try {
            Station station = stationService.update(id, request);
            DTOResponseStation response = new DTOResponseStation(station);

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la parada con el ID proporcionado.");
        }
    }

    @PutMapping("/{id}/add-scooter")
    public ResponseEntity<?> addScooterToStation(@PathVariable Long id, @RequestBody @Validated Long id_monopatin) {
        try {
            Station station = stationService.addScooterToStation(id, id_monopatin);
            DTOResponseStation response = new DTOResponseStation(station);

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la parada con el ID proporcionado.");
        }
    }

  /*
    @PutMapping("/{id}/add-scooter")
    public ResponseEntity<?> parkScooterAtStation(@PathVariable Long id, @RequestBody @Validated Long id_monopatin) {
        try {
            Station station = stationService.addScooterToStation(id, id_monopatin);
            DTOResponseStation response = new DTOResponseStation(station);

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la parada con el ID proporcionado.");
        }
    }

   */

}

