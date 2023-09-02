import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import dto.TotalInvoicedByTheClientDTO;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import dao.ClientDAO;
import dao.InvoiceDAO;
import dao.InvoiceProductDAO;
import dao.ProductDAO;
import factory.AbstractFactory;

public class main {
    private static ClientDAO daoClient;
    private static InvoiceDAO daoInvoice;
    private static InvoiceProductDAO daoInvoiceProduct;
    private static ProductDAO daoProduct;
    private static AbstractFactory factory = AbstractFactory.getDAOFactory(AbstractFactory.MYSQL_DB);
    public static void main(String[] args) throws SQLException, IOException {

        instantiationDAOs();
        createTables();
        populateTables();

        System.out.println("\n" + "Producto que más recaudó: " + "\n");
		System.out.println("\t" + daoProduct.highestGrossingProduct() + "\n");

        System.out.println("\n" + "Listado de clientes ordenado por mayor facturación: " + "\n");
        for (TotalInvoicedByTheClientDTO clientAux : daoClient.getListClientThatInvoiceTheMost() ) {
            System.out.println("\t" + clientAux);
        }

    }


    public static void instantiationDAOs() throws SQLException {
        daoClient = factory.getDAOClient();
        daoInvoice = factory.getDAOInvoice();
        daoInvoiceProduct = factory.getDAOInvoiceProduct();
        daoProduct = factory.getDAOProduct();
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
