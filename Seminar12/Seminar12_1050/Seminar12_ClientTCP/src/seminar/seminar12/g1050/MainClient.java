package seminar.seminar12.g1050;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainClient {

    private static SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy/HH");

    public static void main(String[] args) {
        try(BufferedReader in = new BufferedReader(new InputStreamReader(System.in))){
            int optiune;
            do{
                optiune = citireOptiune(in);
                cerere(optiune,in);
            }
            while (optiune!=3);
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    private static void cerere(int cod,BufferedReader in){
        try(Socket socket = new Socket("localhost",2000)){
            try(ObjectOutputStream cout = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream cin = new ObjectInputStream(socket.getInputStream())){
                cout.writeObject(cod);
                if(cod==3){
                    System.out.println(cin.readObject());
                }
                if (cod==1){
                    Examen examen = getExamen(in);
                    cout.writeObject(examen);
                    System.out.println(cin.readObject());
                }
                if (cod==2){
                    System.out.println("Data examen (in format \"dd.MM.yyyy/HH\")");
                    Date data = fmt.parse(in.readLine().trim());
                    cout.writeObject(data);
                    System.out.println(cin.readObject());
                }
            }
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    private static Examen getExamen(BufferedReader in) throws Exception{
        Examen examen = new Examen();
        System.out.println("Titular:");
        String titular = in.readLine().trim();
        examen.setTitular(titular);
        System.out.println("Data si ora in format \"dd.MM.yyyy/HH\":");
        examen.setData(fmt.parse(in.readLine().trim()));
        System.out.println("Numar studenti:");
        examen.setNumarStudenti(Integer.parseInt(in.readLine().trim()));
        return examen;
    }

    private static int citireOptiune(BufferedReader in) throws Exception{
        System.out.println("1 - Adaugare examen");
        System.out.println("2 - Stergere examen");
        System.out.println("3 - Exit");
        System.out.println("Optiune:");
        return Integer.parseInt(in.readLine().trim());
    }

}
