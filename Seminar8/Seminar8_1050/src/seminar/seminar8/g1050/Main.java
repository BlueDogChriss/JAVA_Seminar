package seminar.seminar8.g1050;

import java.io.File;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try{
//            Creare sectiune critica - obiect monitor
            Sala sala = new Sala(new int[]{10,12,9,13,8,10});
//            Creare fire de executie
//            Parsare folder curent pentru fisiere .csv
            String[] numeFisiere = new File(".").
                    list((path,nume)->nume.endsWith(".csv"));
//            System.out.println(Arrays.toString(numeFisiere));
            Rezervare[] fireRezervare = new Rezervare[numeFisiere.length];
            for (int i=0;i<fireRezervare.length;i++){
                fireRezervare[i] = new Rezervare(sala,numeFisiere[i]);
            }
//            Start fire
            for (Rezervare rezervare:fireRezervare){
                rezervare.start();
            }
//            Sincronizare main cu firele de rezervare
            for (Rezervare rezervare:fireRezervare){
                rezervare.join();
            }
            String[][] s = sala.getSala();
            for (String[] linie:s){
                System.out.println(Arrays.toString(linie));
            }
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }
}
