package DAOFactory;

import java.sql.SQLException;

import DAO.ClientDAO;
import DAO.InvoiceDAO;
import DAO.InvoiceProductDAO;
import DAO.ProductDAO;

/**
 * AbstractFactory
 * The AbstractFactory class is used to create the DAOs.
 */
public abstract class AbstractFactory {

	public abstract ClientDAO getDAOClient() throws SQLException;
	public abstract InvoiceDAO getDAOInvoice() throws SQLException;
	public abstract InvoiceProductDAO getDAOInvoiceProduct() throws SQLException;
	public abstract ProductDAO getDAOProduct() throws SQLException;
	
}
