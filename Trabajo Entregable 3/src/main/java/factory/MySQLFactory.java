package factory;

import repository.CareerRepositoryImp;
import repository.InscriptionRepositoryImp;
import repository.StudentRepositoryImp;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
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