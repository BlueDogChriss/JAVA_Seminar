package seminar.seminar8.g1058.tema10;

import java.io.File;
import java.util.Arrays;

public class MainTema10 {
    public static void main(String[] args) {
        try {
            Sala sala = new Sala(new int[]{10,15,12,13,14});

            String[] numeFisiere = new File(".").list( (cale,nume) -> nume.endsWith(".csv") );
//            System.out.println(Arrays.toString(numeFisiere));
//            Creare fire
            Cerere[] fireCerere = new Cerere[numeFisiere.length];
            for (int i=0;i<fireCerere.length;i++){
                fireCerere[i] = new Cerere(sala,numeFisiere[i]);
            }
//            Start fire
            for (Cerere cerere:fireCerere){
                cerere.start();
            }
//            Sincronizare cu main
            for (Cerere cerere:fireCerere){
                cerere.join();
            }

            String[][] s = sala.getS();
            for (String[] rand:s){
                System.out.println(Arrays.toString(rand));
            }

        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
}
