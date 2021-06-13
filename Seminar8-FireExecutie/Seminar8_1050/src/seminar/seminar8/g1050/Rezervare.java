package seminar.seminar8.g1050;

import java.io.BufferedReader;
import java.io.FileReader;

public class Rezervare extends Thread{

    private Sala sala;
    private String numeFisier;

    public Rezervare(Sala sala, String numeFisier) {
        this.sala = sala;
        this.numeFisier = numeFisier;
    }

    @Override
    public void run() {
//        sala.inc();
        try(BufferedReader in = new BufferedReader(new FileReader(numeFisier))){
            in.lines().forEach( linie->{
                String[] elemente = linie.split(",");
                sala.rezervare(elemente[0].trim(),Integer.parseInt(elemente[1].trim()));
            } );
        }
        catch (Exception ex){
            System.err.println(ex);
        }
//        sala.dec();
    }
}
