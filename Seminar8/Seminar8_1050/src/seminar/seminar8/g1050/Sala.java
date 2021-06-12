package seminar.seminar8.g1050;

public class Sala {
    private String[][] sala;
    private int locuriLibere = 0; //Locuri libere in sala
    private String ultimulFir;
    private int numarFire;

    //    l - Vector cu numarul de locuri pe fiecare rand
    public Sala(int[] l) {
        sala = new String[l.length][];
        for (int i = 0; i < l.length; i++) {
            sala[i] = new String[l[i]];
            locuriLibere += l[i];
        }
    }

    public synchronized void rezervare(String numeSolicitant, int nrLocuri) {
        inc();

        String firCurent = Thread.currentThread().getName();
        if (ultimulFir!=null){
            if (firCurent.equals(ultimulFir) && numarFire>1){
                try{
                    wait();
                }
                catch (InterruptedException ex){}
            }
        }
        ultimulFir = firCurent;
//        Locuri libere disponibile (posibil de ocupat)
        int locuriRezervate = locuriLibere<=nrLocuri?locuriLibere:nrLocuri;
//        k - Contor locuri rezervate
        for (int i=0,k=0;i<sala.length&&k<locuriRezervate;i++){
            for (int j=0;j<sala[i].length&&k<locuriRezervate;j++){
                if (sala[i][j]==null){
                    sala[i][j] = numeSolicitant;
                    locuriLibere--;
                    k++;
                }
            }
        }
        dec();
        notifyAll();
    }

    public String[][] getSala() {
        return sala;
    }

    public void inc(){
        numarFire++;
    }

    public synchronized void dec(){
        numarFire--;
//        notifyAll();
    }
}
