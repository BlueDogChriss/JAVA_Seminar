package seminar.seminar2.g1050;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Scanner;

public class MainSeminar3 {
    public static SimpleDateFormat formatData = new SimpleDateFormat("dd.MM.yyyy");

    public static void main(String[] args) {

//        SEMINAR 1 modificat in seminar 2 prin constructori care arunca exceptii
        try {
            Carte carte = new Carte("Istoria Romana", "III123456", "Enciclopedica",
                    2021, 150, 1950);
            carte.addDomeniu(Domeniu.ISTORIE);
            carte.addAutor("Theodor Mommsen");
            carte.addAutor("Edward Gibbon");
            System.out.println(carte);

            Carte carte1 = carte;
            System.out.println(carte == carte1);
            System.out.println(carte.equals(carte1));

            Carte carte2 = new Carte();
            carte2.setCota("III123456");
            carte2.setTitlu("Data Mining");
            carte2.addDomeniu(Domeniu.IT);
            carte2.addDomeniu(Domeniu.MATEMATICA);
            System.out.println(carte.equals(carte2));

            System.out.println("Vector sortat dupa an aparitie:");

            Carte[] carti = new Carte[2];
            carti[0] = carte;
            carte2.setAnAparitie(2005);
            carti[1] = carte2;
            Arrays.sort(carti);
            System.out.println(Arrays.toString(carti));

//        Seminar 2
            Revista revista = new Revista("Gazeta Matematica", "II876543", "Editura didactica si pedagogica",
                    1978, 12, "Matematica", 12);
            revista.addDomeniu(Domeniu.MATEMATICA);
            System.out.println(revista);
//        Clonare Carte
            Carte copie = (Carte) carte.clone();
            carte.getAutori()[0] = "Popescu Ion";
            carte.getDomenii()[0] = Domeniu.GEOGRAFIE;
            System.out.println("Clonare ---\n" + carte + "\n" + copie);
//            Clonare Revista
            Revista copieRevista = (Revista) revista.clone();
            revista.getDomenii()[0] = Domeniu.IT;
            System.out.println("Clonare ---\n" + revista + "\n" + copieRevista);
//            Test Operatiuni
//            Imprumut
            if (carte.getDataReturnare() == null && carte.getSalaRezervare() == null) {
                carte.imprumut(30);
            }
            System.out.println("Imprumut:\n" + carte);
//            Returnare
            carte.returnare();
            // Rezervare la sala
            if (carte.getDataReturnare() == null && carte.getSalaRezervare() == null) {
                carte.rezervareSala(Sala.GRIGORE_MOISIL);
            }
            System.out.println("Rezervare:\n" + carte);
//                Citire carti din fisier text
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()){
                String[] linie = scanner.nextLine().split(",");
                Carte carteNoua = new Carte();
                carteNoua.setTitlu(linie[0].trim());
                carteNoua.setCota(linie[1].trim());
                carteNoua.setEditura(linie[2].trim());
                carteNoua.setAnAparitie(Integer.parseInt(linie[3].trim()));
                carteNoua.setValoareInventar(Double.parseDouble(linie[4].trim()));
                carteNoua.setNrPagini(Integer.parseInt(linie[5].trim()));

//                ....

            }


        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
}
