package main.resources.tp2.repository;

import main.resources.tp2.dto.DTOReport;
import main.resources.tp2.entity.Career;
import main.resources.tp2.entity.Inscription;

import javax.persistence.EntityManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;

public class InscriptionRepository implements JPARepository<Inscription>{

        private EntityManagerFactory emf;
        private EntityManager em;
        private static InscriptionRepository instance;

        private InscriptionRepository(EntityManagerFactory emf){
            this.emf = emf;
        }
        public static InscriptionRepository getInstance(EntityManagerFactory emf){
            if(instance==null){
                return new InscriptionRepository(emf);
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
    
    public List<DTOReport> createReport() {
    	em = emf.createEntityManager();
    	
    	String query = 
    			"SELECT fj.name as Carrera, fj.año as Año, SUM(fj.inscriptos) as Inscriptos, SUM(fj.egresos) as Egresos\r\n"
    			+ "FROM\r\n"
    			+ "    (\r\n"
    			+ "        SELECT c.name, YEAR(i.fecha_inscripcion) as año, COUNT(*) as inscriptos, '0' as egresos\r\n"
    			+ "        FROM inscription i LEFT JOIN career c ON c.id = i.career_id\r\n"
    			+ "        GROUP BY c.name, año\r\n"
    			+ "        UNION\r\n"
    			+ "        SELECT c.name, YEAR(i.fecha_egreso) AS año, '0' as inscriptos, COUNT(*) AS egresos\r\n"
    			+ "        FROM inscription i RIGHT JOIN career c ON c.id = i.career_id\r\n"
    			+ "        GROUP BY c.name, año HAVING año IS NOT NULL\r\n"
    			+ "    ) as fj\r\n"
    			+ "GROUP BY fj.name, fj.año\r\n"
    			+ "ORDER BY fj.name, fj.año";
    	
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
