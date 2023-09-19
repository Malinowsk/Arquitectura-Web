package main.resources.tp2;

import main.resources.tp2.entity.Career;
import main.resources.tp2.entity.Inscription;
import main.resources.tp2.entity.Student;
import main.resources.tp2.repository.CareerRepository;
import main.resources.tp2.repository.InscriptionRepository;
import main.resources.tp2.repository.StudentRepository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
public class main {

    public static void main(String[] args) throws ParseException {
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Entregable2");
        EntityManager em = emf.createEntityManager();

        StudentRepository studentRepo = new StudentRepository(em);
        CareerRepository carrerRepo = new CareerRepository(em);
        InscriptionRepository inscriptionRepo = new InscriptionRepository(em);

        // 2A) Dar de alta un estudiante
        System.out.println("\n 2.A) Dar de alta un estudiante");

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date date = dateFormat.parse("31/03/1995");
        Student nicolas = new Student(41969641, "Nicolas", "Rodriguez", new Timestamp(date.getTime()), "m", "Rauch");
        date = dateFormat.parse("11/03/1999");
        Student pedro = new Student(34969641, "Pedro", "Albino", new Timestamp(date.getTime()), "m", "Tandil");
        date = dateFormat.parse("20/08/1989");
        Student ana = new Student(34648616, "Ana", "Martinez", new Timestamp(date.getTime()), "f", "Mar del Plata");

        studentRepo.save(nicolas);
        studentRepo.save(pedro);
        studentRepo.save(ana);


        Career tudai = new Career("TUDAI");
        //Career contador = new Career("Contador Publico");
        //Career sistemas = new Career("Ingenieria en Sistemas");
        carrerRepo.save(tudai);
        //carrerRepo.save(contador);
        //carrerRepo.save(sistemas);

        // 2B) Matricular un estudiante en una carrera
        System.out.println("\n 2.B) Matricular un estudiante en una carrera");
        date = dateFormat.parse("31/03/2020");
        Inscription i1 = new Inscription(tudai, nicolas, new Timestamp(date.getTime()), null);
        inscriptionRepo.save(i1);


        System.out.println("------------------------------------------------------------------------------------");

        // 2C) Recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
        // Se ordena por apellido A-Z
        System.out.println("\n 2.C) Listado completo de estudiantes ordenado por apellido:");
        em.getTransaction().begin();
        System.out.println(studentRepo.getAll());
        em.getTransaction().commit();

        System.out.println("------------------------------------------------------------------------------------");

        // 2D) Recuperar un estudiante, en base a su número de libreta universitaria
        System.out.println("\n 2.D) Estudiante cuyo numero de libreta es 2:");
        em.getTransaction().begin();
        System.out.println(studentRepo.getById(2));
        em.getTransaction().commit();

        System.out.println("------------------------------------------------------------------------------------");

        // 2E) Recuperar todos los estudiantes, en base a su género.
        System.out.println("\n 2.E) Estudiantes cuyo genero es masculino:");
        em.getTransaction().begin();
        System.out.println(studentRepo.getByGender("m"));
        em.getTransaction().commit();

        System.out.println("------------------------------------------------------------------------------------");

        // 2F) Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
        em.getTransaction().begin();
        System.out.println("\n 2.F) Carreras con estudiantes inscriptos ordenadas por cantidad de inscriptos:");
        System.out.println(carrerRepo.getCareerOrderByQuantityStudent());
        em.getTransaction().commit();

        System.out.println("------------------------------------------------------------------------------------");

        // 2G) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
        em.getTransaction().begin();
        Long idCarrera = 2L;
        System.out.println("\n 2.G) Estudiantes de la carrera de TUDAI que viven en Rauch:");
        System.out.println(studentRepo.getByCarrerAndCity(idCarrera, "Rauch"));
        em.getTransaction().commit();

        System.out.println("------------------------------------------------------------------------------------");


    }

}
