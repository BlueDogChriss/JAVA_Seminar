package seminar.seminar12.g1050;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Examen implements Serializable {
    private String titular;
    private Date data;
    private int numarStudenti;
    private SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy/HH");

    public Examen() {
    }

    public Examen(Date data) {
        this.data = data;
    }

    public Examen(String titular, Date data, int numarStudenti) {
        this.titular = titular;
        this.data = data;
        this.numarStudenti = numarStudenti;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getNumarStudenti() {
        return numarStudenti;
    }

    public void setNumarStudenti(int numarStudenti) {
        this.numarStudenti = numarStudenti;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Examen examen = (Examen) o;
        return Objects.equals(data, examen.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        return "{"+titular+","+fmt.format(data)+","+numarStudenti+"}";
    }
}
