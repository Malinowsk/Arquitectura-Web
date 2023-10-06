package com.example.trabajoentregable3.controller;

import com.example.trabajoentregable3.dto.DTORequestCareer;
import com.example.trabajoentregable3.service.CareerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("careers")
@RequiredArgsConstructor
public class CareerController {

    @Autowired
    private final CareerService careerService;

    @GetMapping("")
    public List<DTORequestCareer> findAll() {
        return this.careerService.findAll();
    }


    @PostMapping("")
    public ResponseEntity save(@RequestBody @Valid DTORequestCareer cdto){
        return careerService.save(cdto);
    }

 /*   @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Career career){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.careerService.save(career));
        } catch (Exception e2) {
            System.out.println("error " + e2.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }*/
}
