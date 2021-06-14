//Fie datele de intrare (in directorul date):
//
//        a) intretinere_apartamente.txt: lista de apartamente (numar apartament - întreg, suprafata - întreg, numar persoane - întreg) - fișier text de forma:
//        1,100,1
//        2,50,1
//        3,75,2
//        ...
//
//        b) intretinere_facturi.json: fișier text în format JSON cu următoarea structură:
//
//        [
//        {
//        "denumire": "Apa calda",
//        "repartizare": "persoane",
//        "valoare": 800
//        },
//        {
//        "denumire": "Caldura",
//        "repartizare": "suprafata",
//        "valoare": 300
//        },
//        ...
//        ]
//
//        Să se scrie o aplicație Java care să îndeplinească următoarele cerințe:
//
//        1) Să se afișeze la consolă numărul apartamentului cu suprafața maximă
//        Punctaj: 1 punct.
//        Criteriu de acordare a punctajului: afișarea corectă la consolă
//
//        2) Să se afișeze la consolă numărul total de persoane care locuiesc în imobil.
//        Punctaj: 1 punct.
//        Criteriu de acordare a punctajului: afișarea corectă la consolă
//
//        3) Să se afișeze la consolă valoarea totală a facturilor pe fiecare tip de repartizare.
//        Punctaj: 1 punct.
//        Criteriu de acordare a punctajului: afișarea corectă la consolă
//
//        4) Să implementeze funcționalitățile de server și client TCP/IP și să se execute următorul scenariu:
//        - componenta client trimite serverului un număr de apartament
//        - componenta server va întoarce clientului valoarea de plată defalcată pe cele trei tipuri cheltuieli
//        pentru apartamentul respectiv.
//        Serverul se va opri după servirea unei cereri.
//
//        Punctaj:
//        1 punct - afișarea la consolă de către server a numărului apartamentului
//        1 punct - afișarea la consolă de către client a celor trei valori calculate
//        Criteriu de acordare a punctajului: afișarea corectă la consolă

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

class Executa {
    private static List<Apartamente> apartamet;
    private static List<Facturi> facturi;
    public static void main(String[] args){
        try{
//            Cerinta 1
            int supMax = 0;
            int nrApp = 0;

            apartamet = CitireApp();
            System.out.println("Apartamente");
            System.out.println(apartamet);

            System.out.println("Apartament cu sup maxima");

            for(Apartamente a : apartamet){

                if(supMax<a.getSuprafata()){
                    supMax=a.getSuprafata();
                    nrApp=a.getNrApartament();
                }

            }
            System.out.println("Suprafata: "+supMax+" , nr Apartament: "+nrApp);
//            Cerinta 2
            apartamet = CitireApp();
            int nrPers = 0;
            for (Apartamente a1 : apartamet ){
                nrPers = nrPers+a1.getNrPersoane();
            }
            System.out.println("Nr total de persoane imobil: "+nrPers);

//            Cerinta 3  Să se afișeze la consolă valoarea totală a facturilor pe fiecare tip de repartizare.
            System.out.println("Cerinta 3");
            facturi = CitireFacturi();
            ScriereJSON(facturi);

            for(Apartamente a :apartamet) {
                double sumaTotal = 0;
                for (Facturi f : facturi) {
                    if (f.getRepartizare().equals("apartament")) {
                        double platGunoi = f.getValoare();
                        sumaTotal = sumaTotal + platGunoi;
                    }
                    if (f.getRepartizare().equals("suprafata")) {
                        double platCal = f.getValoare() * a.getSuprafata();
                        sumaTotal = sumaTotal + platCal;
                    }
                    if (f.getDenumire().equals("Apa calda") && f.getRepartizare().equals("persoane")) {
                        double platApa = f.getValoare() * a.getNrPersoane();
                        sumaTotal = sumaTotal + platApa;
                    }
                    if (f.getDenumire().equals("Gaze naturale") && f.getRepartizare().equals("persoane")) {
                        double platGaz = f.getValoare() * a.getNrPersoane();
                        sumaTotal = sumaTotal + platGaz;
                    }
                }
                System.out.println("Suma pe apartament "+a.getNrApartament()+" este "+sumaTotal+"\n");
            }

//            Cerinta 4


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static List<Apartamente> CitireApp() throws IOException{
        try(BufferedReader r = new BufferedReader(new FileReader("Arhiva/date/intretinere_apartamente.txt"))) {
            List<Apartamente> list = new ArrayList<>();
            list = r.lines().map(linie->new Apartamente(
                    Integer.parseInt(linie.split(",")[0]),Integer.parseInt(linie.split(",")[1]),Integer.parseInt(linie.split(",")[2])
            )).collect(Collectors.toList());
            return list;
        }
    }
    private static List<Facturi> CitireFacturi() throws IOException{
        try(BufferedReader r = new BufferedReader(new FileReader("Arhiva/date/intretinere_facturi.txt"))) {
            List<Facturi> list = new ArrayList<>();
            list = r.lines().map(linie->new Facturi(
                    linie.split(",")[0],linie.split(",")[1],Integer.parseInt(linie.split(",")[2])
            )).collect(Collectors.toList());
            return list;
        }
    }

    private static void ScriereJSON(List<Facturi> list) throws IOException{

        try(PrintWriter pw = new PrintWriter("Intretinere_facturi2.json")){
            JSONObject js = new JSONObject();
            JSONArray ja = new JSONArray();

            for(var fact : list){
                JSONObject js1 = new JSONObject();
                js1.put("denumire", fact.getDenumire());
                js1.put("repartizare", fact.getRepartizare());
                js1.put("valoare", fact.getValoare());
                ja.put(js1);
            }
            js.put("Lista Facturi", ja);
            js.write(pw,4,0);
        }
    }


}

// clasa Apartament

class Apartamente{
    private int nrApartament;
    private int suprafata;
    private int nrPersoane;
    private List<Apartamente> apartamente = new ArrayList<>();

    public Apartamente() {
    }

    public Apartamente(int nrApartament, int suprafata, int nrPersoane) {
        this.nrApartament = nrApartament;
        this.suprafata = suprafata;
        this.nrPersoane = nrPersoane;
    }

    public int getNrApartament() {
        return nrApartament;
    }

    public void setNrApartament(int nrApartament) {
        this.nrApartament = nrApartament;
    }

    public int getSuprafata() {
        return suprafata;
    }

    public void setSuprafata(int suprafata) {
        this.suprafata = suprafata;
    }

    public int getNrPersoane() {
        return nrPersoane;
    }

    public void setNrPersoane(int nrPersoane) {
        this.nrPersoane = nrPersoane;
    }

    public List<Apartamente> getApartamente() {
        return apartamente;
    }

    public void setApartamente(List<Apartamente> apartamente) {
        this.apartamente = apartamente;
    }

    @Override
    public String toString() {
        return "{" + nrApartament +"," + suprafata +"," + nrPersoane +'}';
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

//clasa Facturi

class Facturi{
    private String denumire;
    private String repartizare;
    private int valoare;
    List<Facturi> facturi = new ArrayList<>();

    public Facturi() {
    }

    public Facturi(String denumire, String repartizare, int valoare) {
        this.denumire = denumire;
        this.repartizare = repartizare;
        this.valoare = valoare;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getRepartizare() {
        return repartizare;
    }

    public void setRepartizare(String repartizare) {
        this.repartizare = repartizare;
    }

    public int getValoare() {
        return valoare;
    }

    public void setValoare(int valoare) {
        this.valoare = valoare;
    }

    public List<Facturi> getFacturi() {
        return facturi;
    }

    public void setFacturi(List<Facturi> facturi) {
        this.facturi = facturi;
    }

    @Override
    public String toString() {
        return "{"+ denumire + ',' + repartizare + ','+ valoare +'}';
    }
}

class Client {
    public static void main(String[] args) throws IOException {
        try(Socket socket = new Socket("localhost",2000)){
            try(ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream()))
            {
                int nrApp = 1;
                out.writeObject(nrApp);
                System.out.println("Plata pentru apartamentu: "+nrApp+" este "+in.readObject());
            }
            catch (Exception e){
                System.err.println(e);
            }
    }
}
}

