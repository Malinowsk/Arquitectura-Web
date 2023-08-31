package DAO;
import Model.InvoiceModel;
import DAOFactory.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class InvoiceDAO implements DAO <InvoiceModel>{
    private Connection connection;

    @Override
    public void cargar(CSVParser datos) throws SQLException {
        connection = ConexionMySQL.getInstance().conectar();
  
		String insert = "INSERT INTO invoice "+
                        "(invoiceId, clientId) "+ 
                        "VALUES (?, ?)"; 
		for(CSVRecord fila: datos) {
			PreparedStatement ps = connection.prepareStatement(insert);
			ps.setInt(1, Integer.parseInt(fila.get("idFactura")));
			ps.setInt(2, Integer.parseInt(fila.get("idCliente")));
			ps.executeUpdate();
			connection.commit();
			ps.close();
		}
		connection.close();
    }

    @Override
    public void createTable() throws SQLException {
        connection = ConexionMySQL.getInstance().conectar();
		String invoice = "CREATE TABLE invoice ( "+
                        "invoiceId INT, "+
                        "clientId INT, "+
                        "PRIMARY KEY (invoiceId), "+
                        "FOREIGN KEY (clientId) " +
                        "references client(clientId)) ";
		connection.prepareStatement(invoice).execute();
		connection.commit();
		connection.close();
    }    
}
