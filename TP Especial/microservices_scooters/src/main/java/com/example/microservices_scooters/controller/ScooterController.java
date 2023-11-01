package com.example.microservices_scooters.controller;

import com.example.microservices_scooters.dto.DTORequestScooter;
import com.example.microservices_scooters.dto.DTOResponseScooter;
import com.example.microservices_scooters.entity.Scooter;
import com.example.microservices_scooters.service.ScooterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/monopatines")
@RequiredArgsConstructor
public class ScooterController {

    @Autowired
    private final ScooterService scooterService;

    @GetMapping("")
    public List<DTOResponseScooter> findAll(){
        return this.scooterService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Monopatin inexistente");
        }
    }

    @GetMapping("reportes/ordenado-por/{ordering}")
    public ResponseEntity<?> getReport(@PathVariable String ordering){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.getReport(ordering));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No se pudo generar el reporte");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody @Validated DTORequestScooter request ){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.save(request));
        }catch(Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Ocurrio un error, revise los campos ingresados");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            this.scooterService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente monopatin con el id: " + id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No se pudo eliminar el monopatin con id: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Validated DTORequestScooter request) {
        try {
            Scooter scooter = scooterService.update(id, request);
            DTOResponseScooter response = new DTOResponseScooter(scooter);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el monopatin con el ID proporcionado.");
        }
    }

    @PutMapping("{id}/paradas/{id_station}")
    public ResponseEntity<?> maintenance(@PathVariable Long id,@PathVariable Long id_station, @RequestBody @Validated DTORequestScooter request) {
        try {
            Scooter scooter = scooterService.maintenance(id_station,id, request);
            DTOResponseScooter response = new DTOResponseScooter(scooter);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el monopatin con el ID proporcionado.");
        }
    }

    @GetMapping("/cantidad-viajes/{cant}/anio/{anio}")
    public ResponseEntity<?> getBySearch(@PathVariable int cant, @PathVariable int anio){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.getBySearch(cant,anio));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Viaje inexistente");
        }

    }

    @GetMapping("/operacion-vs-mantenimiento")
    public ResponseEntity<?> getQuantityBasedOnStatus(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.getQuantityBasedOnStatus());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error.");
        }

    }


}