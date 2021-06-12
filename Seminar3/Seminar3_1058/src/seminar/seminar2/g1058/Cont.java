package seminar.seminar2.g1058;

import java.util.Calendar;
import java.util.Date;

public abstract class Cont {
    private Titular titular;
    private Date dataDeschidere;
    private Moneda moneda;
    private double valoare,dobanda;
    private String sucursala;

    public Cont() {
    }

//    Constructorul arunca exceptie pentra data ulterioara datei curente
    public Cont(Titular titular, Date dataDeschidere, Moneda moneda,
                double valoare, double dobanda, String sucursala) throws Exception {
        if (dataDeschidere.after(new Date())){
            throw new Exception("Data deschidere invalida!");
        }
        this.titular = titular;
        this.dataDeschidere = dataDeschidere;
        this.moneda = moneda;
        this.valoare = valoare;
        this.dobanda = dobanda;
        this.sucursala = sucursala;
    }

    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
    }

    public Date getDataDeschidere() {
        return dataDeschidere;
    }

    public void setDataDeschidere(Date dataDeschidere) throws Exception{
        if (dataDeschidere.after(new Date())){
            throw new Exception("Data deschidere invalida!");
        }
        this.dataDeschidere = dataDeschidere;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public double getValoare() {
        return valoare;
    }

    public void setValoare(double valoare) {
        this.valoare = valoare;
    }

    public double getDobanda() {
        return dobanda;
    }

    public void setDobanda(double dobanda) {
        this.dobanda = dobanda;
    }

    public String getSucursala() {
        return sucursala;
    }

    public void setSucursala(String sucursala) {
        this.sucursala = sucursala;
    }

    @Override
    public String toString() {
        String dataDeschidereS = dataDeschidere==null?"-":MainSeminar3.formatData.format(dataDeschidere);
        return "Cont{" + titular +"," + dataDeschidereS +"," + moneda +
                "," + valoare + "," + dobanda + "," + sucursala +'}';

    }
}
