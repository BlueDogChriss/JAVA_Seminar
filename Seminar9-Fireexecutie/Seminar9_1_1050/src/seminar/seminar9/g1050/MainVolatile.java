package seminar.seminar9.g1050;

import java.util.Timer;
import java.util.TimerTask;

public class MainVolatile {
    public static void main(String[] args) {
        System.out.println("Start aplicatie ...");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Inchideti fortat aplicatia si declarati variabila stop volatile.");
            }
        },10000);
        Task task = new Task(timer);
        task.start();
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException ex){}
        task.setStop(true);
        try{
            task.join();
        }
        catch (InterruptedException ex){}
        System.out.println("Stop aplicatie.");
    }
}
