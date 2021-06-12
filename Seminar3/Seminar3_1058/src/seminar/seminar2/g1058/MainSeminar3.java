package seminar.seminar2.g1058;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Scanner;

public class MainSeminar3 {
    static SimpleDateFormat formatData = new SimpleDateFormat("dd.MM.yyyy");

    public static void main(String[] args) {
        try {

//            Seminar 2

            Adresa adresa1 = new Adresa("Brasov", "Brasov", "1 Decembrie", "2A", 34562);
            Titular titular1 = new Titular("Pop Adrian", 1650304567234L, adresa1);
            Depozit depozit1 = new Depozit(titular1, MainSeminar3.formatData.parse("10.01.2021"),
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

//            Test clonare Titular completat in seminar 3
            Titular titular4 = (Titular) titular1.clone(); // Clonare superficiala
            System.out.println("Obiect original:\n"+titular1+"\nClona:\n"+titular4);
            titular4.getAdresa().setLocalitate("Fagaras");
            System.out.println("Obiect original:\n"+titular1+"\nClona:\n"+titular4);

//            Instantiere ContCurent
            ContCurent contCurent = new ContCurent(titular3,MainSeminar3.formatData.parse("12.02.2021"),
                    Moneda.EURO,15000,0.7,"Pipera","ROCNB0000XXX892376274627");
            System.out.println(contCurent);
//          Calcul dobanda
            System.out.println("Calcul Dobanda\n"+depozit1+"\n"+depozit1.calculDobanda());
//          Depunere numerar
            contCurent.depunereNumerar(2000);
            System.out.println("Depunere numerar\n"+contCurent+"\n"+contCurent.getValoare());
//            Citire depozite din fisierul depozite.csv prin redirectarea inputului standard (consola)
//            Citire prin Scanner
            Depozit[] depozite = new Depozit[0];
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()){
                String[] linie = scanner.nextLine().split(",");
                Depozit depozitNou = new Depozit();
                Titular titular = new Titular();
                Adresa adresa = new Adresa();
                titular.setNume(linie[0].trim());
                titular.setCnp(Long.parseLong(linie[1].trim()));
                adresa.setLocalitate(linie[2].trim());
                adresa.setJudet(linie[3].trim());
                adresa.setStrada(linie[4].trim());
                adresa.setNumar(linie[5].trim());
                adresa.setCod(Integer.parseInt(linie[6].trim()));
                titular.setAdresa(adresa);
                linie = scanner.nextLine().split(",");
                depozitNou.setTipDepozit(TipDepozit.valueOf(linie[0].trim().toUpperCase()));
                depozitNou.setCodContract(Integer.parseInt(linie[1].trim()));
                depozitNou.setDataDeschidere(MainSeminar3.formatData.parse(linie[2].trim()));
                depozitNou.setMoneda(Moneda.valueOf(linie[3].trim().toUpperCase()));
                depozitNou.setValoare(Double.parseDouble(linie[4].trim()));
                depozitNou.setDobanda(Double.parseDouble(linie[5].trim()));
                depozitNou.setSucursala(linie[6].trim());
                depozitNou.setTitular(titular);
                depozite = Arrays.copyOf( depozite,depozite.length+1 );
                depozite[depozite.length-1] = depozitNou;
            }
            System.out.println("Lista depozitelor din fisierul depozite.csv:");
            for (Depozit d:depozite){
                System.out.println(d);
            }
        }
        catch (Exception ex){
            System.err.println(ex);
        }

    }
}
