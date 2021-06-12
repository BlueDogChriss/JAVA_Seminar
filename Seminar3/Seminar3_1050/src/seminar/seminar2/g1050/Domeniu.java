package seminar.seminar2.g1050;

public enum Domeniu {
    ISTORIE(1), MATEMATICA(2), IT(3), GEOGRAFIE(4);
    private int codDomeniu;

    Domeniu(int codDomeniu) {
        this.codDomeniu = codDomeniu;
    }

    public int getCodDomeniu() {
        return codDomeniu;
    }
}
