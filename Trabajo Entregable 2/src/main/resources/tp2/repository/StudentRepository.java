package main.resources.tp2.repository;

import main.resources.tp2.entity.Student;
import javax.persistence.EntityManager;

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
    /*
    public List<Estudiante> getByGender(String gender) {
        TypedQuery<Estudiante> tq = this.em.createNamedQuery(Estudiante.FIND_BY_GENDER, Estudiante.class)
                .setParameter("genero", gender);
        return tq.getResultList();
    }

    public List<Estudiante> getAll() {
        TypedQuery<Estudiante> tq = this.em.createNamedQuery(Estudiante.ORDER_BY_LASTNAME, Estudiante.class);
        return tq.getResultList();
    }

    public List<Estudiante> getById(int id) {
        TypedQuery<Estudiante> tq = this.em.createNamedQuery(Estudiante.FIND_BY_LU, Estudiante.class).setParameter("lu", id);
        return tq.getResultList();
    }

    public List<Estudiante> getByCarrerAndCity(int idCarrera, String ciudad) {
        TypedQuery<Estudiante> tq = this.em.createNamedQuery(Estudiante.FIND_BY_CARREER_CITY, Estudiante.class)
                .setParameter("carrera", idCarrera).setParameter("ciudad", ciudad);
        return tq.getResultList();
    }*/

}