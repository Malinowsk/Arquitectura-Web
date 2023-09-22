package main.resources.tp2.repository;

import main.resources.tp2.entity.Career;
import main.resources.tp2.entity.Inscription;

import javax.persistence.EntityManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
}
