package seminar.seminar9.g1050;

import java.util.Timer;

public class Task extends Thread {
    private long v;
    private volatile boolean stop;
    private Timer timer;

    public Task(Timer timer) {
        this.timer = timer;
    }

    @Override
    public void run() {
        while (!stop){
            v++;v--;
        }
        timer.cancel();
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}
