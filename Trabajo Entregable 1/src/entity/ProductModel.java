package entity;

public class ProductModel {
    // Attributes
    private int productId;
    private String name;
    private Float value;

    public ProductModel(int productId, String name, Float value) {
        this.productId = productId;
        this.name = name;
        this.value = value;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Product Id= " + productId + ", Name= " + name + ", Value= " + value ;
    }
}