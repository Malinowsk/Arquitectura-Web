package com.example.trabajoentregable3.controller;

import com.example.trabajoentregable3.dto.DTOInscription;
import com.example.trabajoentregable3.service.InscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("inscriptions")
@RequiredArgsConstructor
public class InscriptionController {

    private final InscriptionService inscriptionService;

    @GetMapping("")
    public List<DTOInscription> findAll() {
        return this.inscriptionService.findAll();
    }

}
