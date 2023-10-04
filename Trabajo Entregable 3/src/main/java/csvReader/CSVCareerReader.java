package csvReader;

import entity.Career;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.LinkedList;

public class CSVCareerReader extends CSVReader {
    public CSVCareerReader(String path) {
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
