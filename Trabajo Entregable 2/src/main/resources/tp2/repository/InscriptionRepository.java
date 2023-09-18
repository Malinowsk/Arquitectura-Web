package main.resources.tp2.repository;

import main.resources.tp2.entity.Career;
import main.resources.tp2.entity.Inscription;

import javax.persistence.EntityManager;

public class InscriptionRepository implements JPARepository<Inscription>{

    private EntityManager em;

    public InscriptionRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public void save(Inscription i) {
        if (!em.contains(i)) {
            em.getTransaction().begin();
            em.persist(i);
            em.getTransaction().commit();
        } else {
            em.merge(i);
        }
    }
}
