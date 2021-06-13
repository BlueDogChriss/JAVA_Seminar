package seminar.seminar9.g1058;

import java.util.concurrent.Semaphore;

public class Counter {
    private int contor;
    private Semaphore semaphore = new Semaphore(1);

    public void inc1(){
        contor++;
    }

    public void inc2(){
        try{
            semaphore.acquire();
        }
        catch (InterruptedException ex){}
        contor++;
        semaphore.release();
    }

    public int getContor() {
        return contor;
    }
}
