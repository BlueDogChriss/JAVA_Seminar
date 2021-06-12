package seminar.seminar12.g1058;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Eveniment implements Serializable {
    private String numeSolicitant;
    private Date data;
    private int numarLocuri;

    private SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy/HH");

    public Eveniment() {
    }

    public Eveniment(Date data) {
        this.data = data;
    }

    public Eveniment(String numeSolicitant, Date data, int numarLocuri) {
        this.numeSolicitant = numeSolicitant;
        this.data = data;
        this.numarLocuri = numarLocuri;
    }

    public String getNumeSolicitant() {
        return numeSolicitant;
    }

    public void setNumeSolicitant(String numeSolicitant) {
        this.numeSolicitant = numeSolicitant;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getNumarLocuri() {
        return numarLocuri;
    }

    public void setNumarLocuri(int numarLocuri) {
        this.numarLocuri = numarLocuri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Eveniment eveniment = (Eveniment) o;
        return Objects.equals(data, eveniment.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        return "{"+numeSolicitant+","+fmt.format(data)+","+numarLocuri+"}";
    }
}
