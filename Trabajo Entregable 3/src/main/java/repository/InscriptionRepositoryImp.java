package repository;

import dto.DTOReport;
import entity.Inscription;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;

public class InscriptionRepositoryImp implements InscriptionRepository{

        private EntityManagerFactory emf;
        private EntityManager em;
        private static InscriptionRepositoryImp instance;

        private InscriptionRepositoryImp(EntityManagerFactory emf){
            this.emf = emf;
        }

        public static InscriptionRepositoryImp getInstance(EntityManagerFactory emf){
            if(instance==null){
                return new InscriptionRepositoryImp(emf);
            }
            return instance;
        }

    @Override
    public void save(Inscription i) {
        em = emf.createEntityManager();
        if (!em.contains(i)) {
            em.getTransaction().begin();
            em.persist(i);
            em.getTransaction().commit();
        } else {
            em.merge(i);
        }
        em.close();
    }
	@Override
    public List<DTOReport> createReport() {
    	em = emf.createEntityManager();
    	
    	String query = 
    			"SELECT fj.name, fj.year, SUM(fj.enrolled), SUM(fj.graduated)\r\n"
    			+ "FROM\r\n"
    			+ "    (\r\n"
    			+ "        SELECT c.name, YEAR(i.fecha_inscripcion) as year, COUNT(*) as enrolled, '0' as graduated\r\n"
    			+ "        FROM inscription i JOIN career c ON c.id = i.career_id\r\n"
    			+ "        GROUP BY c.name, year\r\n"
    			+ "        UNION\r\n"
    			+ "        SELECT c.name, YEAR(i.fecha_egreso) AS year, '0' as enrolled, COUNT(*) AS graduated\r\n"
    			+ "        FROM inscription i JOIN career c ON c.id = i.career_id\r\n"
    			+ "        GROUP BY c.name, year HAVING year IS NOT NULL\r\n"
    			+ "    ) as fj\r\n"
    			+ "GROUP BY fj.name, fj.year\r\n"
    			+ "ORDER BY fj.name, fj.year";
    	
    	List<Object[]> queryList = this.em.createNativeQuery(query).getResultList();
    	List<DTOReport> report = new ArrayList<DTOReport>();
    	for (Object[] queryListRow : queryList) {
    		DTOReport r = new DTOReport(
    						(String) queryListRow[0],
    						(Integer) queryListRow[1],
    						(Double) queryListRow[2],
    						(Double) queryListRow[3]);
    		report.add(r);
    	}
    	em.close();	
    	return report;
    	
    }
}
