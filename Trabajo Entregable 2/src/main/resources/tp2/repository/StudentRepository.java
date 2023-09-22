package main.resources.tp2.repository;

import main.resources.tp2.entity.Student;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class StudentRepository implements JPARepository<Student> {

    private static StudentRepository instance;
    private EntityManagerFactory emf;
    private EntityManager em;

    private StudentRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public static StudentRepository getInstance(EntityManagerFactory emf){
        if(instance==null){
            return new StudentRepository(emf);
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

    public List<Student> getAll() {
        em = emf.createEntityManager();
        Query query = em.createQuery("SELECT s FROM Student s ORDER BY s.surname");
        List<Student> students = query.getResultList();
        em.close();
        return students;
    }

    public Student getById(long id) {
        em = emf.createEntityManager();
        Student s = em.find(Student.class, id);
        em.close();
        return s;
    }

    public List<Student> getByGender(String gender) {
        em = emf.createEntityManager();
        List<Student> retorno = this.em.createQuery("SELECT s FROM Student s WHERE s.gender = :gender")
                .setParameter("gender", gender)
                .getResultList();
        em.close();
        return retorno;
    }

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