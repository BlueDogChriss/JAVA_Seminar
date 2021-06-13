package seminar.seminar9.g1058;

public class MainDaemon {
    public static void main(String[] args) {
        Thread f1  = new Thread( ()->{
            for (int i=1;i<=10;i++){
                System.out.println("F1 scrie "+i);
                try{
                    Thread.sleep(1000);
                }
                catch (InterruptedException ex){}
            }
        } );
        f1.setDaemon(true);
        f1.start();
//        try{
//            f1.join();
//        }
//        catch (InterruptedException ex){}
        System.out.println("main intra in asteptare ...");
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException ex){}
        System.out.println("main iese din asteptare ...");
    }
}
