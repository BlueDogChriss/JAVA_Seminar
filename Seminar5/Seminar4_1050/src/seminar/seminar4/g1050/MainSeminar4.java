package seminar.seminar4.g1050;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainSeminar4 {
    public static SimpleDateFormat formatData = new SimpleDateFormat("dd.MM.yyyy");

    private Carte[] carti;
    private List<Carte> listaCarti;

    private void seminarii_2_3(){
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
            citire(scanner);
            System.out.println("Carti citite din fisier in vector:");
            for (Carte c:this.carti){
                System.out.println(c);
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public void seminar4(){
        carti[0].rezervareSala(Sala.GRIGORE_MOISIL);
        cartiDisponibile("Disponibil.csv");
        creareLista();
        afisareLista("Lista de carti:");
//        Salvare
        salvare("Carti.dat");
//        Restaurare
        restaurare("Carti.dat");
        afisareLista("Lista carti dupa restaurare:");
        cautareSortare();
    }

    public void cautareSortare(){
//        Cautare pe baza implementarii equals - indexOf()
        Carte carteCautata = new Carte();
        carteCautata.setCota("II223987");
        int k = listaCarti.indexOf(carteCautata);
        if (k==-1){
            System.out.println("Nu a fost gasita cartea cu cota "+carteCautata.getCota());
        } else {
            System.out.println("Cartea cautata este:\n"+listaCarti.get(k));
        }
//        Sortare lista dupa principiul implicit (cel din Comparable)
        Collections.sort(listaCarti);
        afisareLista("Lista cartilor sortate dupa an aparitie:");
//        Creare comparator dupa cota
        Comparator<Carte> comparator = new Comparator<Carte>() {
            @Override
            public int compare(Carte carte1, Carte carte2) {
                return carte1.getCota().compareTo(carte2.getCota());
            }
        };
//        Sortare lista dupa cota
        Collections.sort(listaCarti,comparator );
        afisareLista("Lista cartilor sortate dupa cota:");
//        Cautare binara
        k = Collections.binarySearch(listaCarti,carteCautata,comparator);
        if (k>=0){
            System.out.println("Cartea cu cota "+carteCautata.getCota()+":\n"+listaCarti.get(k));
        } else {
            System.out.println("Nu am gasit cartea cu cota "+carteCautata.getCota()+
                    " pozitia de inserare: "+(-k-1));
        }
    }


    public void restaurare(String numeFisier){
        try(FileInputStream in1 = new FileInputStream(numeFisier);
            ObjectInputStream in = new ObjectInputStream(in1)){
            listaCarti = new ArrayList<>();
            while (in1.available()!=0){
                Carte carte = (Carte) in.readObject();
                listaCarti.add(carte);
            }
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    public void salvare(String numeFisier){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(numeFisier))){
            for (Carte c:listaCarti){
                out.writeObject(c);
            }
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    public void creareLista(){
        listaCarti = new ArrayList<>();
        for (Carte c:carti){
            listaCarti.add(c);
        }
    }

    public void afisareLista(String mesaj){
        System.out.println(mesaj);
        for (Carte c:listaCarti){
            System.out.println(c);
        }
    }

    private void cartiDisponibile(String numeFisier){
        try(PrintWriter out = new PrintWriter(numeFisier)){
            for (Carte c:carti){
                if (c.getDataReturnare()==null && c.getSalaRezervare()==null){
//                    Vor fi salvate cota, titlul și autorii pentru fiecare carte
                    StringBuilder sb = new StringBuilder(c.getCota()+","+c.getTitlu()+",");
                    String[] autori = c.getAutori();
                    for (int i=0;i<autori.length-1;i++){
                        sb.append(autori[i]).append(",");
                    }
                    sb.append(autori[autori.length-1]);
                    out.println(sb);
                }
            }
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    public void citire(String numeFisier) throws Exception{
        citire(new Scanner(new File(numeFisier)));
    }

    private void citire(Scanner scanner) throws Exception{
        carti = new Carte[0];
        while (scanner.hasNextLine()){
            String[] linie = scanner.nextLine().split(",");
            Carte carteNoua = new Carte();
            carteNoua.setTitlu(linie[0].trim());
            carteNoua.setCota(linie[1].trim());
            carteNoua.setEditura(linie[2].trim());
            carteNoua.setAnAparitie(Integer.parseInt(linie[3].trim()));
            carteNoua.setValoareInventar(Double.parseDouble(linie[4].trim()));
            carteNoua.setNrPagini(Integer.parseInt(linie[5].trim()));
            linie = scanner.nextLine().split(",");
            Domeniu[] domenii = new Domeniu[linie.length];
            for (int i=0;i<domenii.length;i++){
                domenii[i] = Domeniu.valueOf(linie[i].trim().toUpperCase());
            }
            carteNoua.setDomenii(domenii);
            linie = scanner.nextLine().split(",");
            carteNoua.setAutori(linie);
            carti = Arrays.copyOf(carti,carti.length+1);
            carti[carti.length-1] = carteNoua;
        }
    }

    public List<Carte> getListaCarti() {
        return listaCarti;
    }

    public static void main(String[] args) {
        MainSeminar4 app = new MainSeminar4();
        app.seminarii_2_3();
        app.seminar4();
    }
}
