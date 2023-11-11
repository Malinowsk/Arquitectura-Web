package com.example.microservices_admin_maintenance.controller;

import com.example.microservices_admin_maintenance.dto.DTORequestScooter;
import com.example.microservices_admin_maintenance.dto.DTORequestScooterModel;
import com.example.microservices_admin_maintenance.dto.DTORequestStatusAccount;
import com.example.microservices_admin_maintenance.service.AdminService;
import lombok.RequiredArgsConstructor;
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
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }

    @PutMapping("/stations/{station_id}")
    public ResponseEntity<?> assignScooterToStation(@PathVariable Long station_id, @RequestBody @Validated DTORequestScooter scooterDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.assignScooterToStation(station_id, scooterDTO));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }


    @PutMapping("/accounts/{id}/account-status")
    public ResponseEntity<?> changeAccountStatus(@PathVariable Long id, @RequestBody @Validated DTORequestStatusAccount accDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.changeAccountStatus(id, accDTO));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }

    @GetMapping("/scooters/amountOfTripsGreaterThan/{trip_qty}/year/{year}")
    public ResponseEntity<?> scooterReportByAmountOfTripsAndYear(@PathVariable int trip_qty, @PathVariable int year) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.scooterReportByAmountOfTripsAndYear(trip_qty, year));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }

    @GetMapping("/rides/totalMoneyEarnedIn/year/{year}/from_month/{start}/to_month/{end}")
    public ResponseEntity<?> amountEarnedInTimePeriod(@PathVariable int year, @PathVariable int start, @PathVariable int end) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.amountEarnedInTimePeriod(year, start, end));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }

    @GetMapping("/scooters/quantity-in-operation")
    public ResponseEntity<?> quantityOfScootersInOperation() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.quantityOfScootersInOperation());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocurrió un error, revise los datos ingresados.");
        }
    }

}
