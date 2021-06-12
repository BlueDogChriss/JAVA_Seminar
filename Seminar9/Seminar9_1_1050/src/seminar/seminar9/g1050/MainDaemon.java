package seminar.seminar9.g1050;

public class MainDaemon {
    public static void main(String[] args) {
        Thread fir1 = new Thread( () -> {
            for (int i=1;i<=10;i++) {
                System.out.println("Fir 1 scrie "+i);
                try{
                    Thread.sleep(1000);
                }
                catch (InterruptedException ex){}
            }
        } );
        fir1.setDaemon(true);
        fir1.start();
        System.out.println("main intra in asteptare ...");
        try{
            Thread.sleep(5000);
        }
        catch (InterruptedException ex){}
        System.out.println("main iese din asteptare ...");
    }
}
