package seminar.seminar8.g1050.muzeu;

import java.util.ArrayDeque;
import java.util.List;

public class Muzeu {
    private ArrayDeque<Integer> vizitatori = new ArrayDeque<>();
    private int v; //capacitate muzeu

    public Muzeu(int v) {
        this.v = v;
    }

    public synchronized void intrare(List<Integer> noiVizitatori){
        if (vizitatori.size()+noiVizitatori.size()>v){
            try {
                wait();
            }
            catch (InterruptedException ex){
            }
        }
        for (int vizitator:noiVizitatori){
            vizitatori.addFirst(vizitator);
        }
        System.out.println("\n Au intrat vizitatorii:"+noiVizitatori);
        System.out.println("\n Muzeu: "+vizitatori.size()+","+vizitatori);
        notifyAll();
    }
    
}
