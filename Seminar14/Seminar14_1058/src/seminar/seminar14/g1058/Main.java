package seminar.seminar14.g1058;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class Tranzactie {
    private int codProdus;
    private double cantitate;

    public Tranzactie() {
    }

    public Tranzactie(int codProdus, double cantitate) {
        this.codProdus = codProdus;
        this.cantitate = cantitate;
    }

    public int getCodProdus() {
        return codProdus;
    }

    public void setCodProdus(int codProdus) {
        this.codProdus = codProdus;
    }

    public double getCantitate() {
        return cantitate;
    }

    public void setCantitate(double cantitate) {
        this.cantitate = cantitate;
    }

    @Override
    public String toString() {
        return "{" + codProdus + "," + cantitate + "}";
    }
}

class Produs {
    private int cod;
    private String denumire;
    private double pret;
    private List<Tranzactie> tranzactii = new ArrayList<>();

    public Produs() {
    }

    public Produs(int cod, String denumire, double pret) {
        this.cod = cod;
        this.denumire = denumire;
        this.pret = pret;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public List<Tranzactie> getTranzactii() {
        return tranzactii;
    }

    public void setTranzactii(List<Tranzactie> tranzactii) {
        this.tranzactii = tranzactii;
    }

    @Override
    public String toString() {
        return "{" + cod + "," + denumire + "," + pret + "," + tranzactii + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produs produs = (Produs) o;
        return cod == produs.cod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cod);
    }
}

public class Main {
    private static List<Produs> produse;

    public static void main(String[] args) {
        try {
//            Cerinta 1
            produse = citesteListaProduse();
            System.out.println("Numarul de produse:" + produse.size());
//            Cerinta 2
            System.out.println("Lista de produse ordonate alfabetic:");
            List<Produs> listaSortata = produse.stream()
                    .sorted((p1, p2) -> p1.getDenumire().compareTo(p2.getDenumire()))
                    .collect(Collectors.toList());
            listaSortata.forEach(System.out::println);
//            Cerinta 3
            citireTranzactii();
//            citireTranzactiiDb(); //Pentru alternativa citirii din baza de date
            raport();
            cerere();

        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    private static void citireTranzactii() throws Exception {
        try (BufferedReader in = new BufferedReader(new FileReader("tranzactii.json"))) {
            JSONTokener t = new JSONTokener(in);
            JSONArray jsTranzactii = new JSONArray(t);
            for (int i = 0; i < jsTranzactii.length(); i++) {
                JSONObject jsTranzactie = jsTranzactii.getJSONObject(i);
                int codProdus = jsTranzactie.getInt("codProdus");
                double cantitate = jsTranzactie.getDouble("cantitate");
                String tip = jsTranzactie.getString("tip");
                if (tip.equals("iesire")) {
                    cantitate = -cantitate;
                }
                Tranzactie tranzactie = new Tranzactie(codProdus, cantitate);
                Produs produs = produse.stream().filter(p -> p.getCod() == codProdus)
                        .findFirst().get();
                if (produs != null) {
                    produs.getTranzactii().add(tranzactie);
                }
            }
        }
    }

    private static void citireTranzactiiDb() throws Exception{
        try(Connection c = DriverManager.getConnection("jdbc:sqlite:s14.db")){
            try(Statement s = c.createStatement();
                ResultSet r = s.executeQuery("select * from TRANZACTII")){
                while (r.next()){
                    int codProdus = r.getInt(1);
                    double cantitate = r.getDouble(2);
                    String tip = r.getString(3);
                    if (tip.equals("iesire")) {
                        cantitate = -cantitate;
                    }
                    Tranzactie tranzactie = new Tranzactie(codProdus, cantitate);
                    Produs produs = produse.stream().filter(p -> p.getCod() == codProdus)
                            .findFirst().get();
                    if (produs != null) {
                        produs.getTranzactii().add(tranzactie);
                    }
                }
            }

        }
    }


    private static void raport() throws Exception {
        try (PrintWriter out = new PrintWriter("lista.csv")) {
            out.println("Denumire produs,Numar tranzactii");
            produse.stream().sorted((p1, p2) -> p1.getTranzactii().size() < p2.getTranzactii().size() ? 1 : -1)
                    .forEach(produs -> {
                        out.println(produs.getDenumire() + "," + produs.getTranzactii().size());
                    });
        }
    }

    private static List<Produs> citesteListaProduse() throws Exception {
        try (BufferedReader in = new BufferedReader(new FileReader("produse.csv"))) {
            return in.lines().map(linie -> {
                String[] t = linie.split(",");
                return new Produs(Integer.parseInt(t[0].trim()), t[1].trim(), Double.parseDouble(t[2].trim()));
            }).collect(Collectors.toList());
        }
    }

    private static void cerere() throws Exception {
        try (ServerSocket socket = new ServerSocket(2000)) {
            socket.setSoTimeout(20000);
            try (Socket s = socket.accept()) {
                try (ObjectInputStream in = new ObjectInputStream(s.getInputStream());
                     ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream())) {
                    int codProdus = (Integer) in.readObject();
                    System.out.println("Serverul primeste codul:"+codProdus);
                    Produs produs = produse.stream().filter(p -> p.getCod() == codProdus)
                            .findFirst().get();
                    if (produs != null) {
                        double v = 0;
                        for (Tranzactie tranzactie : produs.getTranzactii()) {
                            v += tranzactie.getCantitate() * produs.getPret();
                        }
                        out.writeObject(v);
                    } else {
                        out.writeObject(-1);
                    }
                }
            }
        }
    }
}

class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 2000)) {
            try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
                int codProdus = 4;
                out.writeObject(codProdus);
                System.out.println("Valoarea stocului cu codul "+codProdus+" este "+in.readObject());
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
}
