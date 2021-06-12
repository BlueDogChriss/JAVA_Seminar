package seminar.seminar4.g1058;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainSeminar4 {
    static SimpleDateFormat formatData = new SimpleDateFormat("dd.MM.yyyy");

    private Depozit[] depozite;
    private List<Depozit> listaDepozite;

    public void seminarii_2_3(){
        try {

//            Seminar 2

            Adresa adresa1 = new Adresa("Brasov", "Brasov", "1 Decembrie", "2A", 34562);
            Titular titular1 = new Titular("Pop Adrian", 1650304567234L, adresa1);
            Depozit depozit1 = new Depozit(titular1, MainSeminar4.formatData.parse("10.01.2021"),
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
            ContCurent contCurent = new ContCurent(titular3, MainSeminar4.formatData.parse("12.02.2021"),
                    Moneda.EURO,15000,0.7,"Pipera","ROCNB0000XXX892376274627");
            System.out.println(contCurent);
//          Calcul dobanda
            System.out.println("Calcul Dobanda\n"+depozit1+"\n"+depozit1.calculDobanda());
//          Depunere numerar
            contCurent.depunereNumerar(2000);
            System.out.println("Depunere numerar\n"+contCurent+"\n"+contCurent.getValoare());
//            Citire depozite din fisierul depozite.csv prin redirectarea inputului standard (consola)
//            Citire prin Scanner
            Scanner scanner = new Scanner(System.in);
            citire(scanner);
            System.out.println("Lista depozitelor din fisierul depozite.csv:");
            for (Depozit d:depozite){
                System.out.println(d);
            }
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    private void citire(Scanner scanner) throws Exception{
        depozite = new Depozit[0];
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
            depozitNou.setDataDeschidere(MainSeminar4.formatData.parse(linie[2].trim()));
            depozitNou.setMoneda(Moneda.valueOf(linie[3].trim().toUpperCase()));
            depozitNou.setValoare(Double.parseDouble(linie[4].trim()));
            depozitNou.setDobanda(Double.parseDouble(linie[5].trim()));
            depozitNou.setSucursala(linie[6].trim());
            depozitNou.setTitular(titular);
            depozite = Arrays.copyOf( depozite,depozite.length+1 );
            depozite[depozite.length-1] = depozitNou;
        }

    }

    public void seminar4(){
//        Salvare depozite in EURO
        try(PrintWriter out = new PrintWriter("DepoziteEuro.csv")){
            for (Depozit depozit:depozite){
                if (depozit.getMoneda().name().equalsIgnoreCase("euro")){
//                    Salvare pentru numele titularului, codul de contract și valoarea
                    out.println(depozit.getTitular().getNume()+","+
                            depozit.getCodContract()+","+depozit.getValoare());
                }
            }
        }
        catch (Exception ex){
            System.err.println(ex);
        }
//        Creare lista de depozite
        creareLista();
        afisareLista("Lista de depozite:");
//        Salvare (Serializare)
        salvare("depozite.dat");
//        Restaurare lista depozite din fisier (Deserializare)
        restaurare("depozite.dat");
        afisareLista("Lista depozite dupa restaurare:");
        cautareSortate();
    }

    public void cautareSortate(){
//        Cautare dupa cod contract
        Depozit depozitCautat = new Depozit();
        depozitCautat.setCodContract(1111);
        int k = listaDepozite.indexOf(depozitCautat);
        if (k==-1){
            System.out.println("Nu exista depozit cu cod contract"+depozitCautat.getCodContract());
        } else {
            System.out.println("Depozitul cautat:\n"+listaDepozite.get(k));
        }
//        Sortare dupa criteriul implicit (valoare)
        Collections.sort(listaDepozite);
        afisareLista("Lista depozitelor sortate dupa valoare:");
//      Sortare dupa nume titular
        Collections.sort(listaDepozite, new Comparator<Depozit>() {
            @Override
            public int compare(Depozit depozit, Depozit t1) {
                return depozit.getTitular().getNume().compareTo(t1.getTitular().getNume());
            }
        });
        afisareLista("Lista depozitelor sortate dupa numele titularului:");
    }

    public void restaurare(String numeFisier){
        try(FileInputStream in1 = new FileInputStream(numeFisier);
            ObjectInputStream in = new ObjectInputStream(in1)) {
            listaDepozite.clear();
            while (in1.available()!=0){
                Depozit depozit =(Depozit) in.readObject();
                listaDepozite.add(depozit);
            }
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    public void salvare(String numeFisier){
        try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(numeFisier))){
            for (Depozit depozit:listaDepozite){
                out.writeObject(depozit);
            }
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    public void creareLista(){
        if (depozite!=null){
            listaDepozite = new ArrayList<>();
            for (Depozit depozit:depozite){
                listaDepozite.add(depozit);
            }
        }
    }

    public void afisareLista(String mesaj){
        System.out.println(mesaj);
        for (Depozit depozit:listaDepozite){
            System.out.println(depozit);
        }
    }

    public static void main(String[] args) {
        MainSeminar4 app = new MainSeminar4();
        app.seminarii_2_3();
        app.seminar4();
    }
}
