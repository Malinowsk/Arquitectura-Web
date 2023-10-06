package com.example.trabajoentregable3.controller;

import com.example.trabajoentregable3.dto.DTOInscription;
import com.example.trabajoentregable3.dto.DTORequestCareer;
import com.example.trabajoentregable3.dto.DTORequestInscription;
import com.example.trabajoentregable3.service.InscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("inscriptions")
@RequiredArgsConstructor
public class InscriptionController {

    private final InscriptionService inscriptionService;

    @GetMapping("")
    public List<DTOInscription> findAll() {
        return this.inscriptionService.findAll();
    }

    @PostMapping("")
    public ResponseEntity save(@RequestBody @Valid DTORequestInscription instription){
        return inscriptionService.save(instription);
    }


}
