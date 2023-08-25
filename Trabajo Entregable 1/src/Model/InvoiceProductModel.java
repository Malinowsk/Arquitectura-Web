package Model;

public class InvoiceProductModel {
    // Attributes
    private int invoiceId;
    private int productId;
    private int quantity;

    public InvoiceProductModel(int invoiceId, int productId, int quantity) {
        this.invoiceId = invoiceId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getProductId() {
        return productId;

    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "InvoiceProductModel [invoiceId=" + invoiceId + ", productId=" + productId + ", quantity=" + quantity
                + "]";
    }

    
}
