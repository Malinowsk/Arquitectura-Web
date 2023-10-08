package com.example.trabajoentregable3.controller;

import com.example.trabajoentregable3.dto.DTONumberRegisteredPerCareer;
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

    @GetMapping("/sortByCantInsc")
    public List<DTONumberRegisteredPerCareer> findAllOrderByQuantityStudent() {
        return this.careerService.findAllOrderByQuantityStudent();
    }

    @PostMapping("")
    public ResponseEntity save(@RequestBody @Valid DTORequestCareer cdto){
        return careerService.save(cdto);
    }

}
