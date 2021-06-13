package seminar.seminar9.g1058;

public class Departament {
    private String nume,facultate,director;

    public Departament() {
    }

    public Departament(String nume, String facultate, String director) {
        this.nume = nume;
        this.facultate = facultate;
        this.director = director;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getFacultate() {
        return facultate;
    }

    public void setFacultate(String facultate) {
        this.facultate = facultate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Departament{" +
                "nume='" + nume + '\'' +
                ", facultate='" + facultate + '\'' +
                ", director='" + director + '\'' +
                '}';
    }
}
