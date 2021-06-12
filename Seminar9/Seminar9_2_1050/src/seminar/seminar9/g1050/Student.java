package seminar.seminar9.g1050;

public class Student {
    private int id,grupa,anul;
    private String nume;
    private double media;
    private Adresa adresa;

    public Student() {
    }

    public Student(int id, int grupa, int anul, String nume, double media, Adresa adresa) {
        this.id = id;
        this.grupa = grupa;
        this.anul = anul;
        this.nume = nume;
        this.media = media;
        this.adresa = adresa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrupa() {
        return grupa;
    }

    public void setGrupa(int grupa) {
        this.grupa = grupa;
    }

    public int getAnul() {
        return anul;
    }

    public void setAnul(int anul) {
        this.anul = anul;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", grupa=" + grupa +
                ", anul=" + anul +
                ", nume='" + nume + '\'' +
                ", media=" + media +
                ", adresa=" + adresa +
                '}';
    }
}
