package seminar.seminar13.g1050;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private long cnp;
    private String nume;
    private List<Disciplina> catalog = new ArrayList<>();

    public Student() {
    }

    public Student(long cnp, String nume) {
        this.cnp = cnp;
        this.nume = nume;
    }

    public long getCnp() {
        return cnp;
    }

    public void setCnp(long cnp) {
        this.cnp = cnp;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public List<Disciplina> getCatalog() {
        return catalog;
    }

    public void setCatalog(List<Disciplina> catalog) {
        this.catalog = catalog;
    }

    public void add(Disciplina disciplina){
        catalog.add(disciplina);
    }

    @Override
    public String toString() {
        return "{"+cnp+","+nume+","+catalog+"}";
    }
}
