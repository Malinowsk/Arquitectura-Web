package com.example.trabajoentregable3.repository;

import com.example.trabajoentregable3.dto.DTONumberRegisteredPerCareer;
import com.example.trabajoentregable3.entity.Career;

import jakarta.persistence.EntityManager;
import java.util.List;
import jakarta.persistence.EntityManagerFactory;

public class CareerRepositoryImp implements CareerRepository {

    private EntityManagerFactory emf;
    private EntityManager em;
    private static CareerRepositoryImp instance;

    private CareerRepositoryImp(EntityManagerFactory emf){
        this.emf = emf;
    }

    public static CareerRepositoryImp getInstance(EntityManagerFactory emf){
        if(instance==null){
            return new CareerRepositoryImp(emf);
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

    @Override
    public Career getById(long id) {
        em = emf.createEntityManager();
        Career c = em.find(Career.class, id);
        em.close();
        return c;
    }

    @Override
    public List<DTONumberRegisteredPerCareer> getCareerOrderByQuantityStudent() {
        em = emf.createEntityManager();
        @SuppressWarnings("unchecked")
		List<DTONumberRegisteredPerCareer> careerList = 
        		em.createQuery(
        				" SELECT new main.resources.tp2.dto.DTONumberRegisteredPerCareer(c.name, COUNT(s)) "
        				+ " FROM Career c "
        				+ " JOIN c.students s "
        				+ " GROUP BY c.name "
        				+ " ORDER BY COUNT(s) DESC ", DTONumberRegisteredPerCareer.class).getResultList();
        em.close();
        return careerList;
    }

}
