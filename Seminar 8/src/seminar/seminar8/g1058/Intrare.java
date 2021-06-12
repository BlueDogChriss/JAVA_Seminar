package seminar.seminar8.g1058;

import java.util.ArrayList;
import java.util.List;

public class Intrare extends  Thread{
    private Muzeu muzeu;
    private int n;
    private int idVizitator=1;

    public Intrare(Muzeu muzeu, int n) {
        this.muzeu = muzeu;
        this.n = n;
    }

   @Override
    public void run()
   {
        while(!muzeu.isStop())
        {
           int numarVizitatori = (int)(Math.random()*n)+1;
           List<Integer> group = new ArrayList<>();
           for(int i=0;i<numarVizitatori;i++){
               group.add(idVizitator++);
           }
           muzeu.intrare(group);
        }
   }
}
