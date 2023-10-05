package com.example.trabajoentregable3.service;


import com.example.trabajoentregable3.dto.DTOInscription;
import com.example.trabajoentregable3.repository.InscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class InscriptionService {

    private final InscriptionRepository inscriptionRepository;

    public List<DTOInscription> findAll() {

        return this.inscriptionRepository
                .findAll()
                .stream()
                .map(i -> new DTOInscription(i.getFecha_ingreso(),
                        i.getFecha_egreso(),
                        (int) i.getStudent().getUniversityNotebook(),
                        (int) i.getCareer().getId()
                ))
                .toList();
    }
}
