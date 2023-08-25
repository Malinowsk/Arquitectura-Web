package DAO;
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
        connection = ConexionMySQL.conectar();
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
		connection.close();
    }

    @Override
    public void createTable() throws SQLException {
        connection = ConexionMySQL.conectar();
      
		String client = "CREATE TABLE client( "+
                        "clientId INT, "+
                        "clientName VARCHAR(500), "+
                        "clientEmail VARCHAR(150), "+
                        "PRIMARY KEY (clientId)) ";
		connection.prepareStatement(client).execute();
		connection.commit();
		connection.close();
    }

    public ArrayList<ClientModel> getListClientThatInvoiceTheMost() throws SQLException{
        connection = ConexionMySQL.conectar();

        ArrayList<ClientModel> clients = new ArrayList<ClientModel>();

        String query = "SELECT c.*, SUM(p.value * ip.quantity) as Facturado "+
                       "FROM client c JOIN invoice i ON (c.clientId = i.clientId) "+
                       "JOIN invoice_product ip ON(i.invoiceId = ip.invoiceId) "+
                       "JOIN product p ON (p.productId = ip.productId) "+
                       "WHERE c.clientId = i.clientId "+ 
                       "GROUP BY c.clientId "+  
                       "ORDER BY Facturado DESC ";
        
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet result = ps.executeQuery();

        while(result.next()){
            ClientModel client = new ClientModel (result.getInt(1), result.getString(2), result.getString(3));
            clients.add(client);
        }

        connection.commit();
		connection.close();
		ps.close();
		result.close();

        return clients;
    }
}