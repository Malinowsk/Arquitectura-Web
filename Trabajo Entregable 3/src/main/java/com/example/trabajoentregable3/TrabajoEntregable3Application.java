package com.example.trabajoentregable3;

import com.example.trabajoentregable3.entity.Career;
import com.example.trabajoentregable3.entity.Inscription;
import com.example.trabajoentregable3.entity.Student;
import com.example.trabajoentregable3.repository.CareerRepository;
import com.example.trabajoentregable3.repository.InscriptionRepository;
import com.example.trabajoentregable3.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootApplication
public class TrabajoEntregable3Application {

    @Autowired
    private CareerRepository careerRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private InscriptionRepository inscriptionRepository;

    private Student student1, student2, student3, student4, student5, student6, student7;
    private Career ingenieria, tudai, mate;
    private Inscription inscription1, inscription2, inscription3, inscription4, inscription5, inscription6, inscription7, inscription8;


    @PostConstruct
    public void setUp() {
        // Crear y guardar carreras
        ingenieria = new Career(1,"Ingeniería de Sistemas");
        tudai = new Career(2,"TUDAI");
        mate = new Career(3,"Matematicas");
        careerRepository.save(ingenieria);
        careerRepository.save(tudai);
        careerRepository.save(mate);

        // Crear y guardar estudiantes
        student1 = new Student(43344543, "Juan", "Marquez", parseDateToTimestamp("10/05/1997"), "m", "Tandil");
        student2 = new Student(39856762, "Carlos", "Bilardo", parseDateToTimestamp("15/08/1996"), "f", "Mar del Plata");
        student3 = new Student(37654231, "Pedro", "Perez", parseDateToTimestamp("20/11/1998"), "m", "Tandil");
        student4 = new Student(40345543, "David", "Martinez", parseDateToTimestamp("25/03/1999"), "m", "Tandil");
        student5 = new Student(42444555, "Martina", "Rodriguez", parseDateToTimestamp("05/09/1995"), "f", "Tandil");
        student6 = new Student(35978878, "Lucia", "Garcia", parseDateToTimestamp("12/02/1997"), "f", "Rauch");
        student7 = new Student(33223479, "Tomas", "Castro", parseDateToTimestamp("30/07/1998"), "m", "Juarez");

        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
        studentRepository.save(student4);
        studentRepository.save(student5);
        studentRepository.save(student6);
        studentRepository.save(student7);

        // Crear y guardar inscripciones
        inscription1 = new Inscription(ingenieria, student1, parseDateToTimestamp("12/04/2020"), parseDateToTimestamp("12/04/2020"));
        inscription2 = new Inscription(tudai, student2, parseDateToTimestamp("12/04/2020"), parseDateToTimestamp("12/04/2020"));
        inscription3 = new Inscription(mate, student3, parseDateToTimestamp("12/04/2020"), parseDateToTimestamp("12/04/2020"));
        inscription4 = new Inscription(tudai, student4, parseDateToTimestamp("12/04/2020"), parseDateToTimestamp("12/04/2020"));
        inscription5 = new Inscription(ingenieria, student5, parseDateToTimestamp("12/04/2020"), parseDateToTimestamp("12/04/2020"));
        inscription6 = new Inscription(tudai, student6, parseDateToTimestamp("12/04/2020"), parseDateToTimestamp("12/04/2020"));
        inscription7 = new Inscription(tudai, student7, parseDateToTimestamp("12/04/2020"), parseDateToTimestamp("12/04/2020"));
        inscription8 = new Inscription(tudai, student1, parseDateToTimestamp("12/04/2020"), parseDateToTimestamp("12/04/2020"));


        inscriptionRepository.save(inscription1);
        inscriptionRepository.save(inscription2);
        inscriptionRepository.save(inscription3);
        inscriptionRepository.save(inscription4);
        inscriptionRepository.save(inscription5);
        inscriptionRepository.save(inscription6);
        inscriptionRepository.save(inscription7);
        inscriptionRepository.save(inscription8);

    }

    private Timestamp parseDateToTimestamp(String s) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date parsedDate = dateFormat.parse(s);
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Fecha no válida: " + s, e);
        }
    }


    public static void main(String[] args) {

        SpringApplication.run(TrabajoEntregable3Application.class, args);
        System.out.println("Hola mundo");
    }

}
