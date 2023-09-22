package main.resources.tp2.factory;

import main.resources.tp2.repository.CareerRepository;
import main.resources.tp2.repository.InscriptionRepository;
import main.resources.tp2.repository.StudentRepository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

public class MySQLFactory extends FactoryEntityManager {
    private static MySQLFactory instance;
    private EntityManagerFactory emf;

    public static MySQLFactory getInstance() throws SQLException {
        if(instance == null){
            instance = new MySQLFactory();
        }
        return instance;
    }

    private MySQLFactory() {
        this.emf = Persistence.createEntityManagerFactory("MYSQL");
    }
    @Override
    public CareerRepository getCareerRepository() {
        return CareerRepository.getInstance(emf);
    }
    @Override
    public StudentRepository getStudentRepository() {
        return StudentRepository.getInstance(emf);
    }
    @Override
    public InscriptionRepository getInscriptionRepository() {
        return InscriptionRepository.getInstance(emf);
    }

    public void closeEntityManagerFactory() {
        emf.close();
    }

}