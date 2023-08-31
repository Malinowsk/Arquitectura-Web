package DAO;
import Model.ProductModel;
import DAOFactory.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class ProductDAO implements DAO <ProductModel>{
     private Connection connection;

    @Override
    public void cargar(CSVParser datos) throws SQLException {
        connection = ConexionMySQL.getInstance().conectar();
		String insert = "INSERT INTO product "+
                        "(productId, name, value) "+ 
                        "VALUES (?, ?, ?)"; 
		for(CSVRecord fila: datos) {
			PreparedStatement ps = connection.prepareStatement(insert);
			ps.setInt(1, Integer.parseInt(fila.get("idProducto")));
			ps.setString(2, fila.get("nombre"));
			ps.setFloat(3, Float.parseFloat(fila.get("valor")));
			ps.executeUpdate();
			connection.commit();
			ps.close();
		}
		connection.close();
    }


    @Override
    public void createTable() throws SQLException {
        connection = ConexionMySQL.getInstance().conectar();

		String product = "CREATE TABLE product(" +
                                "productId INT," +
                                "name VARCHAR(45)," +
                                "value FLOAT," +
                                "PRIMARY KEY (productId))";
		connection.prepareStatement(product).execute();
		connection.commit();
		connection.close();
    }

    public ProductModel highestGrossingProduct () throws SQLException{
        //we established the connection
        connection = ConexionMySQL.getInstance().conectar();

        //I create a variable to save the product that I collect the most
        ProductModel highestGrossingProduct = null;

        String product = "select p.productId, p.name, sum(p.value * ip.quantity) as recaudo "+
                         "from product p "+
                         "join invoice_product ip ON (p.productId = ip.productId)"+
                         "group by p.productId "+
                         "order by recaudo desc "+
						 "limit 1";

        PreparedStatement query = connection.prepareStatement(product);
        ResultSet result = query.executeQuery();

        while(result.next()) {
			      highestGrossingProduct = new ProductModel(result.getInt(1), 
                                                      result.getString(2), 
                                                      result.getFloat(3));
		    }
		
		query.close();
		result.close();
		connection.close();
		return highestGrossingProduct;
    }
} 
