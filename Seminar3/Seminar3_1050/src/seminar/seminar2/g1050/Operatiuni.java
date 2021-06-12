package seminar.seminar2.g1050;

public interface Operatiuni {
    void imprumut(long nrZile); // Va fi stabilită data returnării ca data curentă + nrZile
    void rezervareSala(Sala numeSala); // Se specifică sala la care este rezervată cartea
    void returnare(); //Returnare carte
}
