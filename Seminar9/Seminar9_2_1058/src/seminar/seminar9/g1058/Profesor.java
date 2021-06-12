package seminar.seminar9.g1058;

public class Profesor {
    private int id;
    private String nume;
    private int varsta;
    private double salariu;
    private Departament departament;

    public Profesor() {
    }

    public Profesor(int id, String nume, int varsta, double salariu,
                    Departament departament) {
        this.id = id;
        this.nume = nume;
        this.varsta = varsta;
        this.salariu = salariu;
        this.departament = departament;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public double getSalariu() {
        return salariu;
    }

    public void setSalariu(double salariu) {
        this.salariu = salariu;
    }

    public Departament getDepartament() {
        return departament;
    }

    public void setDepartament(Departament departament) {
        this.departament = departament;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", varsta=" + varsta +
                ", salariu=" + salariu +
                ", departament=" + departament +
                '}';
    }
}
