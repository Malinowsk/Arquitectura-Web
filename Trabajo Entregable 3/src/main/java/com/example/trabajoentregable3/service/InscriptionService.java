package com.example.trabajoentregable3.service;


import com.example.trabajoentregable3.entity.Inscription;
import com.example.trabajoentregable3.repository.InscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class InscriptionService {

    private final InscriptionRepository inscriptionRepository;

    public List<Inscription> findAll() {
        return this.inscriptionRepository.findAll();
    }
}
