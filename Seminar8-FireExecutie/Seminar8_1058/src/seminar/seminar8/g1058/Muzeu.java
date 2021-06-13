package seminar.seminar8.g1058;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Muzeu {
    private ArrayDeque<Integer> vizitatori = new ArrayDeque<>();
    private int v;
    private boolean stop;

    public Muzeu(int v) {
        this.v = v;
    }

    public synchronized void intrare(List<Integer> vizitatoriNoi){
        while ( vizitatori.size()+vizitatoriNoi.size()>v ){
            try{
                wait();
            }
            catch (InterruptedException ex){}
        }
        for (int vizitator:vizitatoriNoi){
            vizitatori.addFirst(vizitator);
        }
        System.out.println("\nAu intrat vizitatorii: "+vizitatoriNoi);
        System.out.println("Muzeu: "+vizitatori.size()+","+vizitatori);
        notifyAll();
    }

    public synchronized void iesire(int m){
        while (vizitatori.size()==0){
            try {
                wait();
            }
            catch (InterruptedException ex){}
        }
        int numarVizitatori = vizitatori.size();
        int numarVizitatoriIesiti = numarVizitatori<m?numarVizitatori:m;
        List<Integer> vizitatoriIesiti = new ArrayList<>();
        for (int i=0;i<numarVizitatoriIesiti;i++){
            vizitatoriIesiti.add(vizitatori.pollLast());
        }
        System.out.println("\nVizitatori iesiti: "+vizitatoriIesiti);
        System.out.println("Muzeu: "+vizitatori.size()+","+vizitatori);
        notifyAll();
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}
