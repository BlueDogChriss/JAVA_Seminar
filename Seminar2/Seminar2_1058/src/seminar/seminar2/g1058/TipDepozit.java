package seminar.seminar2.g1058;

public enum TipDepozit {
    O_LUNA(30), DOUA_LUNI(60), TREI_LUNI(90), UN_AN(365);

    private int durata;

    TipDepozit(int durata) {
        this.durata = durata;
    }

    public int getDurata() {
        return durata;
    }
}
