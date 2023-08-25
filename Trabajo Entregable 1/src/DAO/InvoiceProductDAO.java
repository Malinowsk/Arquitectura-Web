package DAO;
import Model.InvoiceProductModel;
import DAOFactory.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class InvoiceProductDAO implements DAO <InvoiceProductModel>{
     private Connection connection;

    @Override
    public void cargar(CSVParser datos) throws SQLException {
        connection = ConexionMySQL.conectar();
		String insert = "INSERT INTO invoice_product "+
                        "(invoiceId, productId, quantity) "+ 
                        "VALUES (?, ?, ?)"; 
		for(CSVRecord fila: datos) {
			PreparedStatement ps = connection.prepareStatement(insert);
			ps.setInt(1, Integer.parseInt(fila.get("idFactura")));
			ps.setInt(2, Integer.parseInt(fila.get("idProducto")));
			ps.setInt(3, Integer.parseInt(fila.get("cantidad")));
			ps.executeUpdate();
			connection.commit();
			ps.close();
		}
		connection.close();
    }

    @Override
    public void createTable() throws SQLException {
        connection = ConexionMySQL.conectar();
 
		String invoiceProduct = "CREATE TABLE invoice_product(" +
                                "invoiceId INT," +
                                "productId INT," +
                                "quantity INT," +
                                "PRIMARY KEY (invoiceId, productId), "+
                                "FOREIGN KEY(invoiceId)" +
                                "references invoice(invoiceId)," +
                                "FOREIGN KEY(productId)" +
                                "references product(productId))";
		connection.prepareStatement(invoiceProduct).execute();
		connection.commit();
		connection.close();
    }
}
