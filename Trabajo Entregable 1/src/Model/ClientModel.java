package Model;

public class ClientModel {
    //Attributes
    private int clientId;
    private String clientName;
    private String clientEmail;

    public ClientModel(int clientId, String clientName, String clientEmail) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
    }

    public int getClientId() {
        return clientId;
    }


    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    @Override

    public String toString() {
        return  "Id= " + clientId + " , Name= " + clientName + " , Email= " + clientEmail;
    }

}
