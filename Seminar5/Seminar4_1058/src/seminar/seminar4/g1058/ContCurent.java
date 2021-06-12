package seminar.seminar4.g1058;

import java.util.Date;
import java.util.Objects;

public class ContCurent extends Cont implements Operatiuni{
    private String codIban;

    public ContCurent() {
    }

    public ContCurent(Titular titular, Date dataDeschidere, Moneda moneda, double valoare,
                      double dobanda, String sucursala, String codIban) throws Exception{
        super(titular, dataDeschidere, moneda, valoare, dobanda, sucursala);
        this.codIban = codIban;
    }

    public String getCodIban() {
        return codIban;
    }

    public void setCodIban(String codIban) {
        this.codIban = codIban;
    }

    @Override
    public String toString() {
        return super.toString()+"\nContCurent{" +codIban +"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContCurent that = (ContCurent) o;
        return Objects.equals(codIban, that.codIban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codIban);
    }

    @Override
    public void depunereNumerar(double suma) {
        this.setValoare(this.getValoare()+suma);
    }
}
