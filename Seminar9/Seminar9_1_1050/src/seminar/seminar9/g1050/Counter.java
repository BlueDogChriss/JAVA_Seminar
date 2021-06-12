package seminar.seminar9.g1050;

import java.util.concurrent.Semaphore;

public class Counter {
    private int contor;
    private Object semafor = new Object();
    private Semaphore semaphore = new Semaphore(1);

//    public void incrementare1() {
//        synchronized (semafor) {
//            contor++;
//        }
//    }

    public void incrementare2() {
        contor++;
    }

    public void incrementare3() {
        try {
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
