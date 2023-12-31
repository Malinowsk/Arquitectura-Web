package dao;
import dto.TotalCollectedByProductDTO;
import entity.ProductModel;
import factory.ConexionMySQL;
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
        connection = ConexionMySQL.getInstance().connect();
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
		ConexionMySQL.getInstance().closeConn();
    }


    @Override
    public void createTable() throws SQLException {
        connection = ConexionMySQL.getInstance().connect();

		String product = "CREATE TABLE product(" +
                                "productId INT," +
                                "name VARCHAR(45)," +
                                "value FLOAT," +
                                "PRIMARY KEY (productId))";
		connection.prepareStatement(product).execute();
		connection.commit();
		ConexionMySQL.getInstance().closeConn();
    }

    public TotalCollectedByProductDTO highestGrossingProduct () throws SQLException{
        //we established the connection
        connection = ConexionMySQL.getInstance().connect();

        //I create a variable to save the product that I collect the most
		TotalCollectedByProductDTO highestGrossingProduct = null;

        String product = "select p.productId, p.name, sum(p.value * ip.quantity) as recaudo "+
                         "from product p "+
                         "join invoice_product ip ON (p.productId = ip.productId)"+
                         "group by p.productId "+
                         "order by recaudo desc "+
						 "limit 1";

        PreparedStatement query = connection.prepareStatement(product);
        ResultSet result = query.executeQuery();

        while(result.next()) {
			      highestGrossingProduct = new TotalCollectedByProductDTO(result.getInt(1),
                                                      result.getString(2), 
                                                      result.getFloat(3));
		    }
		
		query.close();
		result.close();
		ConexionMySQL.getInstance().closeConn();
		return highestGrossingProduct;
    }
} 
