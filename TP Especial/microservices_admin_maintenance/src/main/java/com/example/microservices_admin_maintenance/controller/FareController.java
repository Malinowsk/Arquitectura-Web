package com.example.microservices_admin_maintenance.controller;

import com.example.microservices_admin_maintenance.dto.DTOFareRequest;
import com.example.microservices_admin_maintenance.dto.DTOFareResponse;
import com.example.microservices_admin_maintenance.dto.DTOScheduledFareRequest;
import com.example.microservices_admin_maintenance.dto.DTOScheduledFareResponse;
import com.example.microservices_admin_maintenance.service.FareService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/tarifas")
public class FareController {

    private final FareService fareService;

//////////////////////////////////////////// ABM ////////////////////////////////////////////////////////////////////////


    @GetMapping("")
    public List<DTOFareResponse> findAll() { return this.fareService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(fareService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Tarifa inexistente");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> addFare(@RequestBody @Validated DTOFareRequest fDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(fareService.addFare(fDTO));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }

    /*
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFare(@PathVariable Long id, @RequestBody @Validated DTOFareRequest fDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.updateFare(id, fDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFare(@PathVariable Long id) {
        try {
            this.maintenanceService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Se eliminó correctamente la tarifa con id: " + id);
        } catch (Exception e) {

        }
    }
    */

    @GetMapping("/programadas")
    public List<DTOScheduledFareResponse> getScheduledFares() { return this.fareService.findAllScheduledFares(); }


////////////////////////////////////////////SERVICIOS-REPORTES////////////////////////////////////////////////////////////////////////

    //3.f. Como administrador quiero hacer un ajuste de precios, y que a partir de cierta fecha el sistema habilite los nuevos precios.
    @PostMapping("/programadas")
    public ResponseEntity<?> setScheduledFare(@RequestBody @Validated DTOScheduledFareRequest sfDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.fareService.addScheduledFareUpdate(sfDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en datos ingresados.");
        }
    }

}
