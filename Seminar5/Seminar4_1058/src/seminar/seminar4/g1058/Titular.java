package seminar.seminar4.g1058;

import java.io.Serializable;
import java.util.Objects;

public class Titular implements Comparable<Titular>,Cloneable, Serializable {
    private String nume;
    private long cnp;
    private Adresa adresa;

    public Titular() {
    }

    public Titular(String nume, long cnp, Adresa adresa) {
        this.nume = nume;
        this.cnp = cnp;
        this.adresa = adresa;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public long getCnp() {
        return cnp;
    }

    public void setCnp(long cnp) {
        this.cnp = cnp;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        return "Titular{" + nume +"," + cnp +"," + adresa +'}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Titular titular = (Titular) o;
        return cnp == titular.cnp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnp);
    }

    @Override
    public int compareTo(Titular titular) {
        return nume.compareTo(titular.nume);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Titular clona = (Titular) super.clone();
        clona.setAdresa((Adresa) adresa.clone());
        return clona;
    }
}
