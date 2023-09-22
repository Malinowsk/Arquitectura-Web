package main.resources.tp2.factory;

import main.resources.tp2.repository.CareerRepository;
import main.resources.tp2.repository.InscriptionRepository;
import main.resources.tp2.repository.StudentRepository;

import java.sql.SQLException;

public abstract class FactoryEntityManager {
    public static final int MYSQL = 1;

    public abstract CareerRepository getCareerRepository();
    public abstract StudentRepository getStudentRepository();
    public abstract InscriptionRepository getInscriptionRepository();
    public abstract void closeEntityManagerFactory();

    public static FactoryEntityManager getDAOFactory(int persistence) throws SQLException {
        switch (persistence) {
            case MYSQL : return MySQLFactory.getInstance();
            default: return null;
        }
    }
}