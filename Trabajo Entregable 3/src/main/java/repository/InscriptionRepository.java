package repository;

import dto.DTOReport;
import entity.Inscription;

import java.util.List;

public interface InscriptionRepository{

    public void save(Inscription i);
    public List<DTOReport> createReport();
}
