package entity;


public class InvoiceModel {
    // Attributes
    private int invoiceId;
    private int clientId;

    public InvoiceModel(int invoiceId, int clientId) {
        this.invoiceId = invoiceId;
        this.clientId = clientId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "InvoiceModel [clientId=" + clientId + ", invoiceId=" + invoiceId + "]";
    }
}
