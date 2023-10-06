package main.resources.tp2;

import main.resources.tp2.csvReader.*;
import main.resources.tp2.dto.DTONumberRegisteredPerCareer;
import main.resources.tp2.dto.DTOReport;
import main.resources.tp2.dto.DTOStudent;
import main.resources.tp2.entity.Career;
import main.resources.tp2.entity.Inscription;
import main.resources.tp2.entity.Student;
import main.resources.tp2.factory.*;
import main.resources.tp2.repository.CareerRepositoryImp;
import main.resources.tp2.repository.InscriptionRepositoryImp;
import main.resources.tp2.repository.StudentRepositoryImp;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class main {

    private static CareerRepositoryImp careerRepo;
    private static StudentRepositoryImp studentRepo;
    private static InscriptionRepositoryImp inscriptionRepo;

    public static void main(String[] args) throws ParseException, IOException, SQLException {
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE); // no imprime por consola las query del hibernate

        FactoryEntityManager mysqlFactory = FactoryEntityManager.getDAOFactory(FactoryEntityManager.MYSQL);

        careerRepo = mysqlFactory.getCareerRepository();
        studentRepo = mysqlFactory.getStudentRepository();
        inscriptionRepo = mysqlFactory.getInscriptionRepository();


        csvUpload(studentRepo,careerRepo,inscriptionRepo); // se carga los datos de los csv a las tablas


        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

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
        careerRepo.save(tudai);
        Career ing_electronica = new Career("Ingenier�a Electr�nica");
        careerRepo.save(ing_electronica);

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        // 2B) Matricular un estudiante en una carrera
        System.out.println("\n 2.B) Matricular un estudiante en una carrera");
        date = dateFormat.parse("31/03/2020");
        Inscription i1 = new Inscription(tudai, nicolas, new Timestamp(date.getTime()), null);
        inscriptionRepo.save(i1);


        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

        // 2C) Recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
        // Se ordena por apellido A-Z
        System.out.println("\n 2.C) Listado completo de estudiantes ordenado por apellido:");

        for (DTOStudent s : studentRepo.getAll()){
            System.out.println(s);
        }

        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

        // 2D) Recuperar un estudiante, en base a su número de libreta universitaria
        System.out.println("\n 2.D) Estudiante cuyo numero de libreta es 3:");
        System.out.println(studentRepo.getById(3));

        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

        // 2E) Recuperar todos los estudiantes, en base a su género.
        System.out.println("\n 2.E) Estudiantes cuyo genero es masculino:");
        for (DTOStudent s : studentRepo.getByGender("m")){
            System.out.println(s);
        }


        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

        // 2F) Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
        System.out.println("\n 2.F) Carreras con estudiantes inscriptos ordenadas por cantidad de inscriptos:");
        for (DTONumberRegisteredPerCareer c : careerRepo.getCareerOrderByQuantityStudent()){
            System.out.println(c);
        }

        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        // 2G) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.

        Long idCarrera = 6L;
        System.out.println("\n 2.G) Estudiantes de la carrera de TUDAI que viven en Rauch:");
        System.out.println();
        for (DTOStudent s : studentRepo.getByCareerAndCity(idCarrera, "Rauch")){
            System.out.println(s);
        }

        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        
        // 3) Generar un reporte de las carreras, que para cada carrera incluya información de los
        // inscriptos y egresados por año. Se deben ordenar las carreras alfabeticamente, y presentar
        // los años de manera cronológica
        System.out.println("\n 3) Reporte de las carreras ordenadas alfabeticamente, con cantidad de inscriptos y egresados por año.\n");
        for (DTOReport i : inscriptionRepo.createReport()){
            System.out.println(i);
        }
    }

    private static void csvUpload(StudentRepositoryImp studentRepo, CareerRepositoryImp careerRepo, InscriptionRepositoryImp inscriptionRepo) throws IOException, ParseException {

        String filePath = new File("").getAbsolutePath();
        LinkedList<Student> students = new CSVStudentReader(filePath + "./src/main/resources/tp2/csv/student.csv").getStudents();
        for (Student student : students) {
            studentRepo.save(student);
        }

        LinkedList<Career> careers = new CSVCareerReader(filePath + "./src/main/resources/tp2/csv/career.csv").getCareers();
        for (Career career : careers) {
            careerRepo.save(career);
        }
        LinkedList<Inscription> inscriptions = new CSVInscriptionReader(filePath + "./src/main/resources/tp2/csv/inscription.csv").getInscriptions();
        for (Inscription inscription : inscriptions) {
            inscriptionRepo.save(inscription);
        }
    }
}
