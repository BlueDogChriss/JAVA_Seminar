package seminar.seminar8.g1058.tema10;

public class Sala {
    private String[][] s;
    private int numarLocuriLibere;

    public Sala(int[] l) {
        s=new String[l.length][];
        for(int i=0;i<l.length;i++){
            s[i]=new String[l[i]];
            numarLocuriLibere+=l[i];
        }
    }

    public synchronized void rezervare(String numeSolicitant, int nrLocuri){
        int numarLocuriRezervate = numarLocuriLibere<nrLocuri?numarLocuriLibere:nrLocuri;
//         k - contor locuri ocupate
        for(int i=0,k=10;i<s.length && k<numarLocuriRezervate;i++){
            for(int j=0; j<s[i].length && k<numarLocuriRezervate;j++){
                if (s[i][j]==null){
                    s[i][j]=numeSolicitant;
                    numarLocuriLibere--;
                    k++;
                }
            }
        }
    }

    public String[][] getS() {
        return s;
    }
}
