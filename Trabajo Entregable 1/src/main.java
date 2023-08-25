import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import DAO.ClientDAO;
import DAO.InvoiceDAO;
import DAO.InvoiceProductDAO;
import DAO.ProductDAO;
import DAOFactory.AbstractFactory;
import DAOFactory.ConexionMySQL;



public class main {
    private static ClientDAO daoClient;
    private static InvoiceDAO daoInvoice;
    private static InvoiceProductDAO daoInvoiceProduct;
    private static ProductDAO daoProduct;

    private static AbstractFactory factory = new ConexionMySQL();

    public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {

        daoClient = factory.getDAOClient();
        daoInvoice = factory.getDAOInvoice();
        daoInvoiceProduct = factory.getDAOInvoiceProduct();
        daoProduct = factory.getDAOProduct();

        daoClient.createTable();
        daoInvoice.createTable();
        daoProduct.createTable();
        daoInvoiceProduct.createTable();
        

        @SuppressWarnings("deprecation")
		CSVParser client = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/Csv/clientes.csv"));
        
        @SuppressWarnings("deprecation")
        CSVParser invoice = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/Csv/facturas.csv"));

        @SuppressWarnings("deprecation")
        CSVParser invoiceProduct = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/Csv/facturas-productos.csv"));

        @SuppressWarnings("deprecation")
        CSVParser product = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/Csv/productos.csv"));


        daoClient.cargar(client);
        daoInvoice.cargar(invoice);
        daoProduct.cargar(product);
        daoInvoiceProduct.cargar(invoiceProduct);

        System.out.println("Producto que mas recaudo: ");
		System.out.println(daoProduct.highestGrossingProduct());

        System.out.println("Listado de  clientes ordenado por mayor facturacion: ");
		System.out.println(daoClient.getListClientThatInvoiceTheMost());
    }
}
