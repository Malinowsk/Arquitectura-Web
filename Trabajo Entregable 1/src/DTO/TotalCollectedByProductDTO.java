package DTO;

public class TotalCollectedByProductDTO {

    private int productId;
    private String name;
    private Float totalCollected;


    public TotalCollectedByProductDTO(int productId, String name, Float totalCollected) {
        this.productId = productId;
        this.name = name;
        this.totalCollected = totalCollected;
    }


    @Override
    public String toString() {
        return "Identificador= " + productId + ", Nombre= " + name + ", Total Recaudado= " + totalCollected ;
    }

}
