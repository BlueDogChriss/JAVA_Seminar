package seminar.seminar9.g1058;

public class MainSicronizare {
    public static void main(String[] args) {
        Object semafor = new Object(),semafor2 = new Object();
        try{
            Counter counter = new Counter();
            Thread f1 = new Thread( ()->{
                for (int i=0;i<1000;i++){
//                    synchronized (semafor2) {
//                        synchronized (semafor) {
//                            counter.inc1();
//                        }
//                    }
                    counter.inc2();
                }
            });
            Thread f2 = new Thread( ()->{
                for (int i=0;i<1000;i++){
//                    synchronized (semafor) {
//                        synchronized (semafor2) {
//                            counter.inc1();
//                        }
//                    }
                    counter.inc2();
                }
            });
            f1.start();f2.start();
            f1.join();f2.join();
            System.out.println(counter.getContor());
        }
        catch (InterruptedException ex){
            System.err.println(ex);
        }

    }

}
