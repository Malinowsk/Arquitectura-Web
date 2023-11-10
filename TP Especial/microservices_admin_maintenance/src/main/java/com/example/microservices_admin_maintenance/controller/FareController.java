package com.example.microservices_admin_maintenance.controller;

import com.example.microservices_admin_maintenance.dto.DTOFareResponse;
import com.example.microservices_admin_maintenance.dto.DTOScheduledFareRequest;
import com.example.microservices_admin_maintenance.service.FareService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/fares")
public class FareController {

    private final FareService fareService;

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

    @PostMapping("/tarifa-programada")
    public ResponseEntity<?> setScheduledFare(@RequestBody @Validated DTOScheduledFareRequest sfDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.fareService.setScheduledFare(sfDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en datos ingresados.");
        }
    }

}
