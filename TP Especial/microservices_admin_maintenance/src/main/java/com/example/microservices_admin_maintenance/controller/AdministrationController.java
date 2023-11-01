package com.example.microservices_admin_maintenance.controller;

import com.example.microservices_admin_maintenance.dto.DTORequestScooter;
import com.example.microservices_admin_maintenance.dto.DTORequestScooterModel;
import com.example.microservices_admin_maintenance.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
