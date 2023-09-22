package main.resources.tp2.repository;

import main.resources.tp2.entity.Career;
import main.resources.tp2.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class CareerRepository implements JPARepository<Career> {

    private EntityManagerFactory emf;
    private EntityManager em;
    private static CareerRepository instance;

    private CareerRepository(EntityManagerFactory emf){
        this.emf = emf;
    }

    public static CareerRepository getInstance(EntityManagerFactory emf){
        if(instance==null){
            return new CareerRepository(emf);
        }
        return instance;
    }

    @Override
    public void save(Career c) {
        em = emf.createEntityManager();
        if(!em.contains(c)){
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        }
        else{
            em.merge(c);
        }
        em.close();
    }


    public Career getById(long id) {
        em = emf.createEntityManager();
        Career c = em.find(Career.class, id);
        em.close();
        return c;
    }

    public List<List<Career>> getCareerOrderByQuantityStudent() {
        em = emf.createEntityManager();
        List<List<Career>> retorno = em.createQuery("SELECT new List(i.career, COUNT(i.student)) FROM Inscription i GROUP BY i.career ORDER BY COUNT(i.student) DESC").getResultList();
        em.close();
        return retorno;
    }

}
