package seminar.seminar2.g1050;

import java.util.Arrays;

public class MainSeminar2 {
    public static void main(String[] args) {
        Carte carte = new Carte("Istoria Romana","III123456","Enciclopedica",2000,150,1950);
        carte.addDomeniu(Domeniu.ISTORIE);
        carte.addAutor("Theodor Mommsen");
        carte.addAutor("Edward Gibbon");
        System.out.println(carte);

        Carte carte1 = carte;
        System.out.println(carte==carte1);
        System.out.println(carte.equals(carte1));

        Carte carte2 = new Carte();
        carte2.setCota("III123456");
        carte2.setTitlu("Data Mining");
        carte2.addDomeniu(Domeniu.IT);carte2.addDomeniu(Domeniu.MATEMATICA);
        System.out.println(carte.equals(carte2));

        System.out.println("Vector sortat dupa an aparitie:");

        Carte[] carti = new Carte[2];
        carti[0] = carte;
        carte2.setAnAparitie(2005);
        carti[1] = carte2;
        Arrays.sort(carti);
        System.out.println(Arrays.toString(carti));

    }
}
