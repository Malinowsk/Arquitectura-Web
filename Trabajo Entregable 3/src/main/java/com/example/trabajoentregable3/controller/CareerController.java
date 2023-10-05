package com.example.trabajoentregable3.controller;

import com.example.trabajoentregable3.dto.DTOCareer;
import com.example.trabajoentregable3.entity.Career;
import com.example.trabajoentregable3.entity.Student;
import com.example.trabajoentregable3.service.CareerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("careers")
@RequiredArgsConstructor
public class CareerController {

    private final CareerService careerService;

    @GetMapping("/")
    public List<DTOCareer> findAll() {
        return this.careerService.findAll();
    }


    @PostMapping("/")
    public ResponseEntity<Career> save(@RequestBody Career request ){
        return ResponseEntity.accepted().body( this.careerService.save( request ) );
    }
}
