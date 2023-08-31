package DAOFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import DAO.ClientDAO;
import DAO.InvoiceDAO;
import DAO.InvoiceProductDAO;
import DAO.ProductDAO;

public class ConexionMySQL extends AbstractFactory{

	private static Connection conn;
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URI = "jdbc:mysql://localhost/integrador_1";
	private static final String USER = "root";
	private static final String PASS = "";

	private static ConexionMySQL instance = new ConexionMySQL();
	
	ConexionMySQL() {};
	
	public static synchronized ConexionMySQL getInstance() {
		return instance;
	}
	
	public Connection conectar() {
		try {
			Class.forName(DRIVER).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e1) {
			e1.printStackTrace();
			System.exit(1);
		}
		
		try {
			conn = DriverManager.getConnection(URI, USER, PASS);
			conn.setAutoCommit(false);	
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void cerrarConn() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public ClientDAO getDAOClient() throws SQLException {
		return new ClientDAO(); // ver si tiene q ser un singleton
	}

	@Override
	public InvoiceDAO getDAOInvoice() throws SQLException {
		return new InvoiceDAO();
	}

	@Override
	public InvoiceProductDAO getDAOInvoiceProduct() throws SQLException {
		return new InvoiceProductDAO();
	}

	@Override
	public ProductDAO getDAOProduct() throws SQLException {
		return new ProductDAO();
	}

	
}
