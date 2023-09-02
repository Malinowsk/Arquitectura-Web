package factory;

import java.sql.SQLException;

import dao.ClientDAO;
import dao.InvoiceDAO;
import dao.InvoiceProductDAO;
import dao.ProductDAO;

public abstract class AbstractFactory {

	public static final int MYSQL_DB = 1;

	public abstract ClientDAO getDAOClient() throws SQLException;
	public abstract InvoiceDAO getDAOInvoice() throws SQLException;
	public abstract InvoiceProductDAO getDAOInvoiceProduct() throws SQLException;
	public abstract ProductDAO getDAOProduct() throws SQLException;

	public static AbstractFactory getDAOFactory(int factory){
		switch (factory)
		{
			case MYSQL_DB: return ConexionMySQL.getInstance();
			default:return null;
		}
	}

}
