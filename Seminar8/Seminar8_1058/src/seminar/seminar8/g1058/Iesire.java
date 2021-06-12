package seminar.seminar8.g1058;

public class Iesire extends Thread {
    private Muzeu muzeu;
    private int m;

    public Iesire(Muzeu muzeu, int m) {
        this.muzeu = muzeu;
        this.m = m;
    }

    @Override
    public void run() {
        while (!muzeu.isStop()){
            int numarIesiti = (int) (Math.random()*m) + 1;
            muzeu.iesire(numarIesiti);
        }
    }
}
