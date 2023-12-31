package main.resources.tp2.factory;

import main.resources.tp2.repository.CareerRepositoryImp;
import main.resources.tp2.repository.InscriptionRepositoryImp;
import main.resources.tp2.repository.StudentRepositoryImp;

import java.sql.SQLException;

public abstract class FactoryEntityManager {
    public static final int MYSQL = 1;

    public abstract CareerRepositoryImp getCareerRepository();
    public abstract StudentRepositoryImp getStudentRepository();
    public abstract InscriptionRepositoryImp getInscriptionRepository();
    public abstract void closeEntityManagerFactory();

    public static FactoryEntityManager getDAOFactory(int persistence) throws SQLException {
        switch (persistence) {
            case MYSQL : return MySQLFactory.getInstance();
            default: return null;
        }
    }
}