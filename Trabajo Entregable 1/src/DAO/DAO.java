package DAO;

import java.sql.SQLException;
import org.apache.commons.csv.CSVParser;

public interface DAO<T>{

    void cargar(CSVParser datos) throws SQLException;
	void createTable() throws SQLException;
}
