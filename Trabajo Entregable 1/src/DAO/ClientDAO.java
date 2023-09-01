package DAO;
import DTO.TotalInvoicedByTheClientDTO;
import Model.ClientModel;
import DAOFactory.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class ClientDAO implements DAO <ClientModel> {
    private Connection connection;

    @Override
    public void cargar(CSVParser datos) throws SQLException {
        connection = ConexionMySQL.getInstance().conectar();
		String insert = "INSERT INTO client "+
                        "(clientId, clientName, clientEmail) "+ 
                        "VALUES (?, ?, ?)"; 
		for(CSVRecord fila: datos) {
			PreparedStatement ps = connection.prepareStatement(insert);
			ps.setInt(1, Integer.parseInt(fila.get("idCliente")));
			ps.setString(2, fila.get("nombre"));
			ps.setString(3, fila.get("email"));
			ps.executeUpdate();
			connection.commit();
			ps.close();
		}
		ConexionMySQL.getInstance().cerrarConn();
    }

    @Override
    public void createTable() throws SQLException {
        connection = ConexionMySQL.getInstance().conectar();
      
		String client = "CREATE TABLE client( "+
                        "clientId INT, "+
                        "clientName VARCHAR(500), "+
                        "clientEmail VARCHAR(150), "+
                        "PRIMARY KEY (clientId)) ";
		connection.prepareStatement(client).execute();
		connection.commit();
		ConexionMySQL.getInstance().cerrarConn();
    }

    public ArrayList<TotalInvoicedByTheClientDTO> getListClientThatInvoiceTheMost() throws SQLException{
        connection = ConexionMySQL.getInstance().conectar();

        ArrayList<TotalInvoicedByTheClientDTO> clients = new ArrayList<TotalInvoicedByTheClientDTO>();

        String query = "SELECT c.*, SUM(p.value * ip.quantity) as Facturado "+
                       "FROM client c JOIN invoice i ON (c.clientId = i.clientId) "+
                       "JOIN invoice_product ip ON(i.invoiceId = ip.invoiceId) "+
                       "JOIN product p ON (p.productId = ip.productId) "+
                       "GROUP BY c.clientId "+  
                       "ORDER BY Facturado DESC ";
        
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet result = ps.executeQuery();

        while(result.next()){
            TotalInvoicedByTheClientDTO client = new TotalInvoicedByTheClientDTO(result.getInt(1), result.getString(2), result.getFloat(4));
            clients.add(client);
        }

        connection.commit();
		ConexionMySQL.getInstance().cerrarConn();
		ps.close();
		result.close();

        return clients;
    }
}