package seminar.seminar2.g1050;

public enum Sala {
    VICTOR_SLAVESCU("Bibloteca Centrala"), GRIGORE_MOISIL("Facultatea de Matematica"),PETRE_BADEA("Biblioteca Centrala");

    private String unitate;

    Sala(String unitate) {
        this.unitate = unitate;
    }

    public String getUnitate() {
        return unitate;
    }
}
