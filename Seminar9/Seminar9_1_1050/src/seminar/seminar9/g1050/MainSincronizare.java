package seminar.seminar9.g1050;

public class MainSincronizare {
    public static void main(String[] args) {
        Object semafor = new Object(), semafor2 = new Object();
        try {
            Counter counter = new Counter();
            Thread fir1 = new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    counter.incrementare3();
//                    synchronized (semafor2) {
//                        synchronized (semafor) {
//                            counter.incrementare2();
//                        }
//                    }
                }
            });
            Thread fir2 = new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    counter.incrementare3();
//                    synchronized (semafor) {
//                        synchronized (semafor2) {
//                            counter.incrementare2();
//                        }
//                    }
                }
            });
            fir2.start();
            fir1.start();
            fir1.join();
            fir2.join();
            System.out.println(counter.getContor());
        } catch (InterruptedException ex) {
            System.err.println(ex);
        }
    }
}
