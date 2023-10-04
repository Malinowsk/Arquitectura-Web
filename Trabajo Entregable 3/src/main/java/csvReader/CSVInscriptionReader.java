package csvReader;

import entity.*;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class CSVInscriptionReader extends CSVReader {


    public CSVInscriptionReader(String path) {
        super(path);
    }

    public LinkedList<Inscription> getInscriptions() throws IOException, ParseException {
        Iterable<CSVRecord> records = this.read();
        LinkedList<Inscription> inscriptions = new LinkedList<>();
        for (CSVRecord record : records) {
            Student student = new Student(Integer.parseInt(record.get(0)));
            Career career = new Career(Integer.parseInt(record.get(1)));

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date initDate = parseDateOrNull(record.get(2), dateFormat);
            Date dueDate = parseDateOrNull(record.get(3), dateFormat);

            inscriptions.add(new Inscription(career, student, initDate != null ? new Timestamp(initDate.getTime()) : null, dueDate != null ? new Timestamp(dueDate.getTime()) : null));
        }
        return inscriptions;
    }

    private Date parseDateOrNull(String dateStr, SimpleDateFormat dateFormat) throws ParseException {
        if (dateStr == null || dateStr.equalsIgnoreCase("null")) {
            return null;
        }
        return dateFormat.parse(dateStr);
    }

}

/*

SELECT insc.name , insc.Año Inscripción, insc.Cantidad Inscriptos, egres.Cantidad Egresados
FROM (
        SELECT c.name, YEAR(i.fecha_inscripcion) AS "Año Inscripción",COUNT(i.fecha_inscripcion) AS "Cantidad Inscriptos"
                FROM inscription i
                JOIN career c ON i.career_id = c.id
                GROUP BY YEAR(i.fecha_inscripcion), c.name
                ORDER BY YEAR(i.fecha_inscripcion) ASC;
        ) insc
        full join
        (
        SELECT c.name, YEAR(i.fecha_egreso) AS "Año Egreso",COUNT(i.fecha_egreso) AS "Cantidad Egresados"
        FROM inscription i
        JOIN career c ON i.career_id = c.id
        WHERE i.fecha_egreso IS NOT NULL
        GROUP BY YEAR(i.fecha_egreso), c.name
        ORDER BY YEAR(i.fecha_egreso) ASC;
        ) egres
        on ( insc.name == egres.name and insc.Año Inscripción == egres.Año Egreso )*/
