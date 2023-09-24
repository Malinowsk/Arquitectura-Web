package main.resources.tp2.repository;

import main.resources.tp2.dto.DTOStudent;
import main.resources.tp2.entity.Student;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

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
        //List<Student> students = query.getResultList();
        List<Student> studentList = query.getResultList();
        List<DTOStudent> studentsDTOList = this.buildDTOStudentList(studentList);
        em.close();
        return studentsDTOList;
    }
    @Override
    public Student getById(long id) {
        em = emf.createEntityManager();
        Student s = em.find(Student.class, id);
        em.close();
        return s;
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
    
    /*private DTOStudent createDTOStudent(long l, Integer docNumber, String fullName,
    		String gender, String city, Timestamp birthdate) {
    	String fullName = (String) row[5] + ", " + (String) row[6]; 
    	DTOStudent s = new DTOStudent(
				(Integer) row[0], 
				(Integer) row[3], 
				fullName, 
				(String) row[4],
				(String) row[2], 
				(Timestamp) row[1]);
    	return s;
    }*/
    
    private List<DTOStudent> buildDTOStudentList (List<Student> studentList) {
    	List<DTOStudent> DTOStudentList = new ArrayList<DTOStudent>();
    	for (Student student : studentList) {
    		DTOStudentList.add(new DTOStudent(
        			(int) student.getUniversityNotebook(),
        			(int) student.getDocumentNumber(),
        			student.getSurname() + ", " + student.getName(),
        			student.getGender(),
        			student.getCity(),
        			student.getBirthdate()));        	
        }
    	return DTOStudentList;
    }

}