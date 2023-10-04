package com.example.trabajoentregable3.repository;

import com.example.trabajoentregable3.dto.DTOReport;
import com.example.trabajoentregable3.entity.Inscription;

import java.util.List;

public interface InscriptionRepository{

    public void save(Inscription i);
    public List<DTOReport> createReport();
}
