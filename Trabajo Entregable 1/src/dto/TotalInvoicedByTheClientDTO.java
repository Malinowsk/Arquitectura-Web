package dto;

public class TotalInvoicedByTheClientDTO {

    private int idcliente;
    private String nombre;
    private float totalFacturado;

    public TotalInvoicedByTheClientDTO(int idcliente, String nombre, float totalFacturado) {
        this.idcliente = idcliente;
        this.nombre = nombre;
        this.totalFacturado = totalFacturado;
    }

    public String toString() {
        return  "Identificador= " + idcliente + " , Nombre= " + nombre + " , Total Facturado= " + totalFacturado;
    }
}
