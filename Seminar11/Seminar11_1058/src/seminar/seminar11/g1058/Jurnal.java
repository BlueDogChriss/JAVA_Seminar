package seminar.seminar11.g1058;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Jurnal {
    private Cont contd,contc;
    private Date dataI;
    private double suma;

    public Jurnal() {
    }

    public Jurnal(Cont contd, Cont contc, Date dataI, double suma) {
        this.contd = contd;
        this.contc = contc;
        this.dataI = dataI;
        this.suma = suma;
    }

    public Cont getContd() {
        return contd;
    }

    public void setContd(Cont contd) {
        this.contd = contd;
    }

    public Cont getContc() {
        return contc;
    }

    public void setContc(Cont contc) {
        this.contc = contc;
    }

    public Date getDataI() {
        return dataI;
    }

    public void setDataI(Date dataI) {
        this.dataI = dataI;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    @Override
    public String toString() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
        return "{"+contd+"-"+contc+":"+suma+","+fmt.format(dataI)+"}";
    }
}
