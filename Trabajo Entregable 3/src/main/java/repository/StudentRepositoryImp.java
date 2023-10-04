package repository;

import dto.DTOStudent;
import entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

import java.sql.Timestamp;
import java.util.ArrayList;
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
    public List<DTOStudent> getAll() {
        em = emf.createEntityManager();
        Query query = em.createQuery("SELECT s FROM Student s ORDER BY s.surname");
        List<Student> studentList = query.getResultList();
        List<DTOStudent> studentsDTOList = this.buildDTOStudentList(studentList);
        em.close();
        return studentsDTOList;
    }
    @Override
    public DTOStudent getById(long id) {
        em = emf.createEntityManager();
        Student s = em.find(Student.class, id);
        return this.buildDTOStudent(s);
    }
    @Override
    public List<DTOStudent> getByGender(String gender) {
        em = emf.createEntityManager();
        List<Student> studentList = this.em.createQuery("SELECT s FROM Student s WHERE s.gender = :gender")
                .setParameter("gender", gender)
                .getResultList();
        List<DTOStudent> studentsByGender = this.buildDTOStudentList(studentList);
        em.close();
        return studentsByGender;
    }
    @Override
    public List<DTOStudent> getByCareerAndCity(Long idCarrera, String ciudad) {
        em = emf.createEntityManager();
        Query q = em.createQuery("SELECT s FROM Student s, IN(s.registrations) r WHERE r.career.id = :idCarrera AND s.city = :ciudadOrigen");
        q.setParameter("idCarrera", idCarrera);
        q.setParameter("ciudadOrigen", ciudad);
        List<Student> studentList = q.getResultList();
        List<DTOStudent> studentsByCareerAndCity = this.buildDTOStudentList(studentList);
        em.close();
        return studentsByCareerAndCity;
    }
    
    private List<DTOStudent> buildDTOStudentList (List<Student> studentList) {
    	List<DTOStudent> DTOStudentList = new ArrayList<DTOStudent>();
    	for (Student student : studentList) {
    		DTOStudentList.add(buildDTOStudent(student));        	
        }
    	return DTOStudentList;
    }
    
    private DTOStudent buildDTOStudent (Student student) {
    	DTOStudent studentDTO = new DTOStudent(
    			(int) student.getUniversityNotebook(),
    			(int) student.getDocumentNumber(),
                student.getName() + " " + student.getSurname(),
    			student.getGender(),
    			student.getCity(),
    			student.getBirthdate());  
    	return studentDTO;
    }

}