package main.resources.tp2.repository;

import main.resources.tp2.entity.Career;
import main.resources.tp2.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class CareerRepository implements JPARepository<Career> {

    private EntityManager em = null;

    public CareerRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Career c) {
        if(!em.contains(c)){
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        }
        else{
            em.merge(c);
        }
    }


    public Career getById(long id) {
        return em.find(Career.class, id);
    }

    public List<List<Career>> getCareerOrderByQuantityStudent() {
       return em.createQuery("SELECT new List(i.career, COUNT(i.student)) FROM Inscription i GROUP BY i.career ORDER BY COUNT(i.student) DESC").getResultList();
    }

}
