package main.resources.tp2.repository;

import main.resources.tp2.entity.Student;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class StudentRepositoryImp implements StudentRepository {

    private static StudentRepositoryImp instance;
    private EntityManagerFactory emf;
    private EntityManager em;

    private StudentRepositoryImp(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public static StudentRepositoryImp getInstance(EntityManagerFactory emf){
        if(instance==null){
            return new StudentRepositoryImp(emf);
        }
        return instance;
    }

    @Override
    public void save(Student e) {
        em = emf.createEntityManager();
        if (!em.contains(e)) {
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
        } else {
            em.merge(e);
        }
        em.close();
    }
    @Override
    public List<Student> getAll() {
        em = emf.createEntityManager();
        Query query = em.createQuery("SELECT s FROM Student s ORDER BY s.surname");
        List<Student> students = query.getResultList();
        em.close();
        return students;
    }
    @Override
    public Student getById(long id) {
        em = emf.createEntityManager();
        Student s = em.find(Student.class, id);
        em.close();
        return s;
    }
    @Override
    public List<Student> getByGender(String gender) {
        em = emf.createEntityManager();
        List<Student> retorno = this.em.createQuery("SELECT s FROM Student s WHERE s.gender = :gender")
                .setParameter("gender", gender)
                .getResultList();
        em.close();
        return retorno;
    }
    @Override
    public List<Student> getByCarrerAndCity(Long idCarrera, String ciudad) {
        em = emf.createEntityManager();
        Query q = em.createQuery("SELECT s FROM Student s, IN(s.registrations) r WHERE r.career.id = :idCarrera AND s.city = :ciudadOrigen");
        q.setParameter("idCarrera", idCarrera);
        q.setParameter("ciudadOrigen", ciudad);
        List<Student> students = q.getResultList();
        em.close();
        return students;
    }

}