package seminar.seminar12.g1058;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.List;

public class MainClient {
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy/HH");

    public static void main(String[] args) {
        try(BufferedReader cin = new BufferedReader(new InputStreamReader(System.in))){
            int optiune;
            do {
                optiune = getOptiune(cin);
                cerere(optiune,cin);
            }
            while (optiune!=3);
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    private static void cerere(int optiune,BufferedReader cin){
        try(Socket socket = new Socket("localhost",2000)){
            try(ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){
                out.writeObject(optiune);
                if (optiune==3){
                    System.out.println(in.readObject());
                }
                if (optiune == 2){
                    System.out.println("Nume sau parte din nume:");
                    String nume = cin.readLine().trim();
                    out.writeObject(nume);
                    List<Eveniment> evenimente = (List<Eveniment>) in.readObject();
                    evenimente.forEach(System.out::println);
                }
                if (optiune==1){
                    Eveniment eveniment = getEveniment(cin);
                    out.writeObject(eveniment);
                    System.out.println(in.readObject());
                }
            }
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    private static Eveniment getEveniment(BufferedReader cin) throws Exception{
        Eveniment eveniment = new Eveniment();
        System.out.println("Nume solicitant:");
        eveniment.setNumeSolicitant(cin.readLine().trim());
        System.out.println("Data si ora (in format dd.MM.yyyy/HH):");
        eveniment.setData(fmt.parse(cin.readLine().trim()));
        System.out.println("Numar locuri:");
        eveniment.setNumarLocuri(Integer.parseInt(cin.readLine().trim()));
        return eveniment;
    }

    private static int getOptiune(BufferedReader cin) throws Exception{
        System.out.println("1 - Adaugare eveniment");
        System.out.println("2 - Interogare dupa nume solicitant");
        System.out.println("3 - Exit");
        System.out.println("Optiune:");
        return Integer.parseInt(cin.readLine().trim());
    }

}
