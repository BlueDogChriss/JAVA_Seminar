package seminar.seminar4.g1050;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class Carte extends Publicatie implements Operatiuni {
    private String[] autori;
    private int nrPagini;
    private Sala salaRezervare;
    private Date dataReturnare;

    public Carte() {
    }

    public Carte(String titlu, String cota, String editura, int anAparitie, double valoareInventar, int nrPagini) throws Exception {
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
        String dataReturnareString = dataReturnare==null?"-": MainSeminar4.formatData.format(dataReturnare);
        String salaRezervareS = salaRezervare==null?"-":salaRezervare.toString();
        return super.toString()+"\nCarte{" + Arrays.toString(autori) + ","
                + nrPagini + "," + salaRezervareS + "," + dataReturnareString + "}";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Carte copie = (Carte) super.clone();
        copie.setAutori(autori.clone());
        return copie;
    }

    @Override
    public void imprumut(long nrZile) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH,(int)nrZile);
        dataReturnare = calendar.getTime();
    }

    @Override
    public void rezervareSala(Sala numeSala) {
        salaRezervare = numeSala;
    }

    @Override
    public void returnare() {
        salaRezervare = null;
        dataReturnare = null;
    }
}
