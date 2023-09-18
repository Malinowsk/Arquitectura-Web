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
public class main {

    public static void main(String[] args) throws ParseException {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Entregable2");
        EntityManager em = emf.createEntityManager();


        StudentRepository studentRepo = new StudentRepository(em);
        CareerRepository carrerRepo = new CareerRepository(em);
        InscriptionRepository inscriptionRepo = new InscriptionRepository(em);


        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date date = dateFormat.parse("31/03/1995");
        Student nicolas = new Student(41969641, "Nicolas", "Rodriguez", new Timestamp(date.getTime()), "m", "Rauch");
        studentRepo.save(nicolas);


        Career tudai = new Career("TUDAI");
        //Career contador = new Career("Contador Publico");
        //Career sistemas = new Career("Ingenieria en Sistemas");
        carrerRepo.save(tudai);
        //carrerRepo.save(contador);
        //carrerRepo.save(sistemas);

        date = dateFormat.parse("31/03/2020");
        Inscription i1 = new Inscription(tudai, nicolas, new Timestamp(date.getTime()), null);
        inscriptionRepo.save(i1);

    }

}
