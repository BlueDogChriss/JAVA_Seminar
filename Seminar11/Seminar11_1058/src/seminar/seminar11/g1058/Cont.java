package seminar.seminar11.g1058;

import java.util.Objects;

public class Cont {
    private int cod;
    private String denumire;

    public Cont() {
    }

    public Cont(int cod) {
        this.cod = cod;
    }

    public Cont(int cod, String denumire) {
        this.cod = cod;
        this.denumire = denumire;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    @Override
    public String toString() {
        return cod+","+denumire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cont cont = (Cont) o;
        return cod == cont.cod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cod);
    }
}
