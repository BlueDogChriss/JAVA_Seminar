package seminar.seminar13.g1058;

import javax.xml.transform.stream.StreamResult;

public class Client {
    private int codClient;
    private String numeClient;
    private double cantitate;

    public Client() {
    }

    public Client(int codClient, String numeClient, double cantitate) {
        this.codClient = codClient;
        this.numeClient = numeClient;
        this.cantitate = cantitate;
    }

    public int getCodClient() {
        return codClient;
    }

    public void setCodClient(int codClient) {
        this.codClient = codClient;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public double getCantitate() {
        return cantitate;
    }

    public void setCantitate(double cantitate) {
        this.cantitate = cantitate;
    }

    @Override
    public String toString() {
        return "{"+codClient+","+numeClient+","+cantitate+"}";
    }
}
