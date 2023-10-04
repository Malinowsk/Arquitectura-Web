package com.example.trabajoentregable3.controller;

import com.example.trabajoentregable3.service.CareerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.trabajoentregable3.entity.Career;

import java.util.List;

@RestController
@RequestMapping("careers")
@RequiredArgsConstructor
public class CareerController {

    private final CareerService careerService;

    @GetMapping("/")
    public List<Career> findAll() {
        return this.careerService.findAll();
    }
}
