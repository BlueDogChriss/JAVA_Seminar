package seminar.seminar8.g1058;

public class Main {
    public static void main(String [] args){
        try{
            System.out.println("Start scenariu ...");
            Muzeu muzeu = new Muzeu (30);
            Intrare intrare = new Intrare (muzeu, 5);
            Iesire iesire = new Iesire(muzeu, 7);
            iesire.start();
            intrare.start();
            Thread.sleep(100);
            muzeu.setStop(true);
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }

}
