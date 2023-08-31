package DAOFactory;

import java.sql.SQLException;

import DAO.ClientDAO;
import DAO.InvoiceDAO;
import DAO.InvoiceProductDAO;
import DAO.ProductDAO;

public abstract class AbstractFactory {

	public static final int MYSQL_DB = 1;
	public static final int DERBY_DB = 2;
	public abstract ClientDAO getDAOClient() throws SQLException;
	public abstract InvoiceDAO getDAOInvoice() throws SQLException;
	public abstract InvoiceProductDAO getDAOInvoiceProduct() throws SQLException;
	public abstract ProductDAO getDAOProduct() throws SQLException;

	private static ConexionMySQL factoryMySQL = new ConexionMySQL();
	public static AbstractFactory getDAOFactory(int factory){
		switch (factory)
		{
			case MYSQL_DB: return factoryMySQL.getInstance();
			default:return null;
		}
	}

}
