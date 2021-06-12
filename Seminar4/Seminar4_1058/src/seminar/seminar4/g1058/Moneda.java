package seminar.seminar4.g1058;

public enum Moneda {
    LEU(1), EURO(2), DOLAR(3);

    private int codValuta;

    Moneda(int codValuta) {
        this.codValuta = codValuta;
    }

    public int getCodValuta() {
        return codValuta;
    }
}
