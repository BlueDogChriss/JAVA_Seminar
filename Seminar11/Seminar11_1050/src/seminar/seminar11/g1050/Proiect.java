package seminar.seminar11.g1050;

import java.util.Objects;

public class Proiect {
    private String acronim,finantator;
    private Titular titular;
    private double valoare;

    public Proiect() {
    }

    public Proiect(String acronim) {
        this.acronim = acronim;
    }

    public Proiect(String acronim, String finantator, Titular titular, double valoare) {
        this.acronim = acronim;
        this.finantator = finantator;
        this.titular = titular;
        this.valoare = valoare;
    }

    public String getAcronim() {
        return acronim;
    }

    public void setAcronim(String acronim) {
        this.acronim = acronim;
    }

    public String getFinantator() {
        return finantator;
    }

    public void setFinantator(String finantator) {
        this.finantator = finantator;
    }

    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
    }

    public double getValoare() {
        return valoare;
    }

    public void setValoare(double valoare) {
        this.valoare = valoare;
    }

    @Override
    public String toString() {
        return "{"+acronim+","+titular+","+finantator+","+valoare+"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proiect proiect = (Proiect) o;
        return Objects.equals(acronim, proiect.acronim);
    }

    @Override
    public int hashCode() {
        return Objects.hash(acronim);
    }
}
