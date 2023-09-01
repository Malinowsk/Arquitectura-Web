import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import DTO.TotalInvoicedByTheClientDTO;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import DAO.ClientDAO;
import DAO.InvoiceDAO;
import DAO.InvoiceProductDAO;
import DAO.ProductDAO;
import DAOFactory.AbstractFactory;

public class main {
    private static ClientDAO daoClient;
    private static InvoiceDAO daoInvoice;
    private static InvoiceProductDAO daoInvoiceProduct;
    private static ProductDAO daoProduct;
    private static AbstractFactory factory = AbstractFactory.getDAOFactory(AbstractFactory.MYSQL_DB);
    public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {

        createTables();
        populateTables();

        System.out.println("\n" + "Producto que más recaudo: " + "\n");
		System.out.println("\t" + daoProduct.highestGrossingProduct() + "\n");

        System.out.println("\n" + "Listado de clientes ordenado por mayor facturación: " + "\n");
        for (TotalInvoicedByTheClientDTO clientAux : daoClient.getListClientThatInvoiceTheMost() ) {
            System.out.println("\t" + clientAux);
        }

    }

    public static void createTables() throws SQLException {
        daoClient.createTable();
        daoInvoice.createTable();
        daoProduct.createTable();
        daoInvoiceProduct.createTable();
    }

    public static void populateTables() throws SQLException, IOException {
        CSVParser client = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/Csv/clientes.csv"));
        CSVParser invoice = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/Csv/facturas.csv"));
        CSVParser invoiceProduct = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/Csv/facturas-productos.csv"));
        CSVParser product = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/Csv/productos.csv"));

        daoClient.cargar(client);
        daoInvoice.cargar(invoice);
        daoProduct.cargar(product);
        daoInvoiceProduct.cargar(invoiceProduct);
    }

}
