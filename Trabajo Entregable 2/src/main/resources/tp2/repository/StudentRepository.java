package main.resources.tp2.repository;

import main.resources.tp2.entity.Student;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class StudentRepository implements JPARepository<Student> {
    private EntityManager em;

    public StudentRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Student e) {
        if (!em.contains(e)) {
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
        } else {
            em.merge(e);
        }
    }

    public List<Student> getAll() {
        Query query = em.createQuery("SELECT s FROM Student s ORDER BY s.surname");
        List<Student> students = query.getResultList();
        return students;
    }

    public Student getById(long id) {
        return em.find(Student.class, id);
    }

    /*
    *
     * Obtiene un estudiante por su libretaUniversitaria
     * 2. d) recuperar un estudiante, en base a su número de libreta universitaria.
     */
    /*
    public Student getEstudianteByLibretaUniversitaria(int libretaUniversitaria) {
        return em.find(Student.class, em);
    }*/
  /*
    /**
     * Obtiene todos los estudiantes por un determinado genero
     *
     * @param gender
     * @return retorna una lista de estudiantes de un genero
     */

    public List<Student> getByGender(String gender) {
        return this.em.createQuery("SELECT s FROM Student s WHERE s.gender = :gender")
                .setParameter("gender", gender)
                .getResultList();
    }

/*


    public List<Estudiante> getByCarrerAndCity(int idCarrera, String ciudad) {
        TypedQuery<Estudiante> tq = this.em.createNamedQuery(Estudiante.FIND_BY_CARREER_CITY, Estudiante.class)
                .setParameter("carrera", idCarrera).setParameter("ciudad", ciudad);
        return tq.getResultList();
    }
*/

    public List<Student> getByCarrerAndCity(Long idCarrera, String ciudad) {
        Query q = em.createQuery("SELECT s FROM Student s, IN(s.registrations) r WHERE r.career.id = :idCarrera AND s.city = :ciudadOrigen");
        q.setParameter("idCarrera", idCarrera);
        q.setParameter("ciudadOrigen", ciudad);
        List<Student> students = q.getResultList();
        return students;
    }

}