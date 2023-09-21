package main.resources.tp2.csvReader;

import main.resources.tp2.entity.Student;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class CSVStudentReader extends CSVReader{

    public CSVStudentReader(String path) {
        super(path);
    }

    public LinkedList<Student> getStudents() throws IOException, ParseException {
        Iterable<CSVRecord> records =  this.read();
        LinkedList<Student> students = new LinkedList<>();
        for (CSVRecord record : records) {
            int documentNumber = Integer.parseInt(record.get(1));
            String name = (record.get(2));
            String surname = (record.get(3));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date birthdate = dateFormat.parse(record.get(4));
            String gender = (record.get(5));
            String city = (record.get(6));
            students.add(new Student(documentNumber, name, surname, new Timestamp(birthdate.getTime()), gender, city));
        }
        return students;
    }
}
