package seminar.seminar9.g1050;

public class Adresa {
    private String judet,localitate,strada,nr;

    public Adresa() {
    }

    public Adresa(String judet, String localitate, String strada, String nr) {
        this.judet = judet;
        this.localitate = localitate;
        this.strada = strada;
        this.nr = nr;
    }

    public String getJudet() {
        return judet;
    }

    public void setJudet(String judet) {
        this.judet = judet;
    }

    public String getLocalitate() {
        return localitate;
    }

    public void setLocalitate(String localitate) {
        this.localitate = localitate;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    @Override
    public String toString() {
        return "Adresa{" +
                "judet='" + judet + '\'' +
                ", localitate='" + localitate + '\'' +
                ", strada='" + strada + '\'' +
                ", nr='" + nr + '\'' +
                '}';
    }
}
