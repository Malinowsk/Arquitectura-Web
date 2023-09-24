package main.resources.tp2.factory;

import main.resources.tp2.repository.CareerRepositoryImp;
import main.resources.tp2.repository.InscriptionRepositoryImp;
import main.resources.tp2.repository.StudentRepositoryImp;

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
    public CareerRepositoryImp getCareerRepository() {
        return CareerRepositoryImp.getInstance(emf);
    }
    @Override
    public StudentRepositoryImp getStudentRepository() {
        return StudentRepositoryImp.getInstance(emf);
    }
    @Override
    public InscriptionRepositoryImp getInscriptionRepository() {
        return InscriptionRepositoryImp.getInstance(emf);
    }

    public void closeEntityManagerFactory() {
        emf.close();
    }

}