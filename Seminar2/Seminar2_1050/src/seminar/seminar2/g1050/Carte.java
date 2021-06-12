package seminar.seminar2.g1050;

import java.util.Arrays;
import java.util.Date;

public class Carte extends Publicatie {
    private String[] autori;
    private int nrPagini;
    private Sala salaRezervare;
    private Date dataReturnare;

    public Carte() {
    }

    public Carte(String titlu, String cota, String editura, int anAparitie, double valoareInventar, int nrPagini) {
        super(titlu, cota, editura, anAparitie, valoareInventar);
        this.nrPagini = nrPagini;
    }

    public String[] getAutori() {
        return autori;
    }

    public void setAutori(String[] autori) {
        this.autori = autori;
    }

    public int getNrPagini() {
        return nrPagini;
    }

    public void setNrPagini(int nrPagini) {
        this.nrPagini = nrPagini;
    }

    public Sala getSalaRezervare() {
        return salaRezervare;
    }

    public Date getDataReturnare() {
        return dataReturnare;
    }

    public void addAutor(String autorNou){
        if (autori==null){
            autori = new String[1];
        } else {
            autori = Arrays.copyOf(autori,autori.length+1);
        }
        autori[autori.length-1] = autorNou;
    }

    @Override
    public String toString() {
        return super.toString()+"\nCarte{" + Arrays.toString(autori) + ","
                + nrPagini + "," + salaRezervare + "," + dataReturnare + "}";
    }
}
