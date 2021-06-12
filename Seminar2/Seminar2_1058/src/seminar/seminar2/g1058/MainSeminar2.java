package seminar.seminar2.g1058;

import java.text.SimpleDateFormat;
import java.util.Arrays;

public class MainSeminar2 {
    public static void main(String[] args) {
        try {
            Adresa adresa1 = new Adresa("Brasov", "Brasov", "1 Decembrie", "2A", 34562);
            Titular titular1 = new Titular("Pop Adrian", 1650304567234L, adresa1);
            SimpleDateFormat formatData = new SimpleDateFormat("dd.MM.yyyy");
            Depozit depozit1 = new Depozit(titular1, formatData.parse("10.01.2021"),
                    Moneda.LEU, 10000, 0.5, "Brasov", TipDepozit.TREI_LUNI, 1010);
            System.out.println(depozit1);

//            Test egalitate Titular
            Titular titular2 = titular1;
            System.out.println(titular1==titular2);
            System.out.println(titular1.equals(titular2));

            Adresa adresa2 = new Adresa("Covasna","Covasna","Mihai Eminescu","10",4040);
            Titular titular3 = new Titular("Muntean Loredana",1650304567234L,adresa2);
            System.out.println(titular1.equals(titular3));

            Titular[] titulari = new Titular[2];
            titulari[0] = titular1;titulari[1]=titular3;
            Arrays.sort(titulari);
            System.out.println(Arrays.toString(titulari));


//          Test comparator Titular
            if (titular1.compareTo(titular3)<0){
                System.out.println("titlular1 < titular2");
            } else if (titular1.compareTo(titular3)==0){
                System.out.println("titlular1 == titular2");
            } else {
                System.out.println("titlular1 > titular2");
            }

//            Test clonare Titular
            Titular titular4 = (Titular) titular1.clone(); // Clonare superficiala
            System.out.println("Obiect original:\n"+titular1+"\nClona:\n"+titular4);
            titular4.getAdresa().setLocalitate("Fagaras");
            System.out.println("Obiect original:\n"+titular1+"\nClona:\n"+titular4);

        }
        catch (Exception ex){
            System.err.println(ex);
        }

    }
}
