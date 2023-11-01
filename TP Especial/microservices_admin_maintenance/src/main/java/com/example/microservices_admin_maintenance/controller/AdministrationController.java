package com.example.microservices_admin_maintenance.controller;

import com.example.microservices_admin_maintenance.dto.DTOFareRequest;
import com.example.microservices_admin_maintenance.dto.DTORequestScooter;
import com.example.microservices_admin_maintenance.dto.DTORequestScooterModel;
import com.example.microservices_admin_maintenance.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin")
public class AdministrationController {

    private final AdminService adminService;

    @PostMapping("/scooters")
    public ResponseEntity<?> createScooter(@RequestBody @Validated DTORequestScooterModel scooterModel) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.createScooter(scooterModel));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }

    @PutMapping("/stations/{station_id}")
    public ResponseEntity<?> assignScooterToStation(@PathVariable Long station_id, @RequestBody @Validated DTORequestScooter scooterDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.assignScooterToStation(station_id, scooterDTO));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }

    @PostMapping("/fares")
    public ResponseEntity<?> addFare(@RequestBody @Validated DTOFareRequest fDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(adminService.addFare(fDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }

    /*
    TODO: por ahora quedan comentados los metodos de PUT y DELETE de tarifas, discutir donde deben ir las tarifas para
          calcular el costo del viaje
    @PutMapping("/fare/{id}")
    public ResponseEntity<?> updateFare(@PathVariable Long id, @RequestBody @Validated DTOFareRequest fDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.updateFare(id, fDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }

    @DeleteMapping("/fare/{id}")
    public ResponseEntity<?> deleteFare(@PathVariable Long id) {
        try {
            this.maintenanceService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Se eliminó correctamente la tarifa con id: " + id);
        } catch (Exception e) {

        }
    }
    */

    @PatchMapping("/accounts/{id}/account-status")
    public ResponseEntity<?> changeAccountStatus(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.changeAccountStatus(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }

}
