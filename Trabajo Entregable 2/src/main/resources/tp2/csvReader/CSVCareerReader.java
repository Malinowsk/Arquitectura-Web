package main.resources.tp2.csvReader;

import main.resources.tp2.entity.Career;
import main.resources.tp2.entity.Student;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class CSVCarrerReader extends CSVReader {
    public CSVCarrerReader(String path) {
        super(path);
    }

    public LinkedList<Career> getCareers() throws IOException {
        Iterable<CSVRecord> records =  this.read();
        LinkedList<Career> careers = new LinkedList<>();
        for (CSVRecord record : records) {
            String name = record.get(1);
            careers.add(new Career(name));
        }
        return careers;
    }

}
