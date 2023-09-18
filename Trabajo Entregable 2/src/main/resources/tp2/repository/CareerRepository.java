package main.resources.tp2.repository;

import main.resources.tp2.entity.Career;
import main.resources.tp2.entity.Student;

import javax.persistence.EntityManager;

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
}
