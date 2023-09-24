package main.resources.tp2.repository;

import main.resources.tp2.dto.DTOReport;
import main.resources.tp2.entity.Inscription;

import java.util.List;

public interface InscriptionRepository{

    public void save(Inscription i);
    public List<DTOReport> createReport();
}
