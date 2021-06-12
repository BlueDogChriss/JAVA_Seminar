package seminar.seminar13.g1050;

public class Disciplina {
    private String numeDisciplina;
    private int numarCredite,nota;

    public Disciplina() {
    }

    public Disciplina(String numeDisciplina, int numarCredite, int nota) {
        this.numeDisciplina = numeDisciplina;
        this.numarCredite = numarCredite;
        this.nota = nota;
    }

    public String getNumeDisciplina() {
        return numeDisciplina;
    }

    public void setNumeDisciplina(String numeDisciplina) {
        this.numeDisciplina = numeDisciplina;
    }

    public int getNumarCredite() {
        return numarCredite;
    }

    public void setNumarCredite(int numarCredite) {
        this.numarCredite = numarCredite;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "{"+numeDisciplina+","+numarCredite+","+nota+"}";
    }
}
