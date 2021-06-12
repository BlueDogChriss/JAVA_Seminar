package seminar.seminar13.g1058;

import java.util.ArrayList;
import java.util.List;

public class Produs {
    private int codProdus;
    private String denumireProdus;
    private double pretUnitar;
    private List<Client> clienti = new ArrayList<>();

    public Produs() {
    }

    public Produs(int codProdus, String denumireProdus, double pretUnitar) {
        this.codProdus = codProdus;
        this.denumireProdus = denumireProdus;
        this.pretUnitar = pretUnitar;
    }

    public int getCodProdus() {
        return codProdus;
    }

    public void setCodProdus(int codProdus) {
        this.codProdus = codProdus;
    }

    public String getDenumireProdus() {
        return denumireProdus;
    }

    public void setDenumireProdus(String denumireProdus) {
        this.denumireProdus = denumireProdus;
    }

    public double getPretUnitar() {
        return pretUnitar;
    }

    public void setPretUnitar(double pretUnitar) {
        this.pretUnitar = pretUnitar;
    }

    public List<Client> getClienti() {
        return clienti;
    }

    public void setClienti(List<Client> clienti) {
        this.clienti = clienti;
    }

    public void add(Client client){
        clienti.add(client);
    }

    @Override
    public String toString() {
        return "{"+codProdus+","+denumireProdus+","+pretUnitar+","+clienti+"}";
    }
}
