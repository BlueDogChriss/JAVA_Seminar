package seminar.seminar2.g1050;

import java.util.Arrays;
import java.util.Objects;

public abstract class Publicatie implements Comparable<Publicatie> {
    private String titlu, cota, editura;
    private int anAparitie;
    private double valoareInventar;
    private Domeniu[] domenii;

    public Publicatie() {
    }

    public Publicatie(String titlu, String cota, String editura, int anAparitie, double valoareInventar) {
        this.titlu = titlu;
        this.cota = cota;
        this.editura = editura;
        this.anAparitie = anAparitie;
        this.valoareInventar = valoareInventar;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getCota() {
        return cota;
    }

    public void setCota(String cota) {
        this.cota = cota;
    }

    public String getEditura() {
        return editura;
    }

    public void setEditura(String editura) {
        this.editura = editura;
    }

    public int getAnAparitie() {
        return anAparitie;
    }

    public void setAnAparitie(int anAparitie) {
        this.anAparitie = anAparitie;
    }

    public double getValoareInventar() {
        return valoareInventar;
    }

    public void setValoareInventar(double valoareInventar) {
        this.valoareInventar = valoareInventar;
    }

    public Domeniu[] getDomenii() {
        return domenii;
    }

    public void setDomenii(Domeniu[] domenii) {
        this.domenii = domenii;
    }

    public void addDomeniu(Domeniu domeniu){
        if (domenii==null){
            domenii = new Domeniu[1];
        } else {
            domenii = Arrays.copyOf(domenii,domenii.length+1);
        }
        domenii[domenii.length-1] = domeniu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publicatie that = (Publicatie) o;
        return cota.equals(that.cota);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cota);
    }

    @Override
    public String toString() {
        return "Publicatie {" +titlu + "," + cota + "," + editura + "," + anAparitie +"," + valoareInventar +
                "," + Arrays.toString(domenii) + '}';
    }

    @Override
    public int compareTo(Publicatie publicatie) {
        if (anAparitie==publicatie.anAparitie){
            return 0;
        } else {
            if (anAparitie< publicatie.anAparitie){
                return -1;
            } else {
                return 1;
            }
        }
    }
}
