package com.example.microservices_admin_maintenance.controller;

import com.example.microservices_admin_maintenance.dto.DTORequestMaintenance;
import com.example.microservices_admin_maintenance.service.MaintenanceService;
import com.example.microservices_admin_maintenance.dto.DTOResponseMaintenance;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/maintenance")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @GetMapping("")
    public List<DTOResponseMaintenance> findAll(){ return this.maintenanceService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(maintenanceService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Orden de mantenimiento inexistente");
        }
    }

    //Agregar Monopatín a Mantenimiento, Comunicarse con Mic Monopatines para cambiar su estado y removerlo de la parada
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody @Validated DTORequestMaintenance rDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(maintenanceService.save(rDTO));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            this.maintenanceService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Se eliminó correctamente el registro de mantenimiento con id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No se pudo eliminar el registro de mantenimiento con id: " + id);
        }
    }

    /*
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Validated DTORequestMaintenance rDTO) {
        try {
            DTOResponseMaintenance response = maintenanceService.update(id, rDTO);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el registro de mantenimiento con el ID proporcionado.");
        }
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<?> finishMaintenance(@PathVariable Long id) {
        try {
            DTOResponseMaintenance response = maintenanceService.endScooterMaintenance(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el registro de mantenimiento con el ID proporcionado.");
        }
    }

}
