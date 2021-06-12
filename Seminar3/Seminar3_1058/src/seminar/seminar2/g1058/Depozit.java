package seminar.seminar2.g1058;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Depozit extends Cont implements OperatiuniDepozite {
    private TipDepozit tipDepozit;
    private int codContract;

    public Depozit() {
    }

    public Depozit(Titular titular, Date dataDeschidere, Moneda moneda, double valoare,
                   double dobanda, String sucursala, TipDepozit tipDepozit, int codContract) throws Exception{
        super(titular, dataDeschidere, moneda, valoare, dobanda, sucursala);
        this.tipDepozit = tipDepozit;
        this.codContract = codContract;
    }

    public TipDepozit getTipDepozit() {
        return tipDepozit;
    }

    public void setTipDepozit(TipDepozit tipDepozit) {
        this.tipDepozit = tipDepozit;
    }

    public int getCodContract() {
        return codContract;
    }

    public void setCodContract(int codContract) {
        this.codContract = codContract;
    }

    @Override
    public String toString() {
        return super.toString()+"\nDepozit{" +tipDepozit +"," + codContract +"}";
    }

    @Override
    public double calculDobanda() {
        Date azi = new Date();
        long nrZile = TimeUnit.DAYS.convert( azi.getTime()-getDataDeschidere().getTime(),TimeUnit.MILLISECONDS);
        double valoareDobanda = getDobanda()*getValoare()*nrZile/ (365*100);
        return valoareDobanda;
    }
}
