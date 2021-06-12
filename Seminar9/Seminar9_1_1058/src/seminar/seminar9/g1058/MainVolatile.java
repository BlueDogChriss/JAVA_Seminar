package seminar.seminar9.g1058;

import java.util.Timer;
import java.util.TimerTask;

public class MainVolatile {
    public static void main(String[] args) {
        System.out.println("Start main ...");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Task blocat. \nVariabila stop trebuie sa fie volatile!");
            }
        },5000);
        Task task = new Task(timer);
        task.start();
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException ex){}
        task.setStop(true);
        System.out.println("Sfarsit main ...");
    }
}
