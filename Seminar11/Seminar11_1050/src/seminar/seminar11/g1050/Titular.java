package seminar.seminar11.g1050;

import java.util.Objects;

public class Titular {
    private long cnp;
    private String nume,departament;

    public Titular() {
    }

    public Titular(long cnp) {
        this.cnp = cnp;
    }

    public Titular(long cnp, String nume, String departament) {
        this.cnp = cnp;
        this.nume = nume;
        this.departament = departament;
    }

    public long getCnp() {
        return cnp;
    }

    public void setCnp(long cnp) {
        this.cnp = cnp;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    @Override
    public String toString() {
        return cnp+","+nume+","+departament;
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
}
