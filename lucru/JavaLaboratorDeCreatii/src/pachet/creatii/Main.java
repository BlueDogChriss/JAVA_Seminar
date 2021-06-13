package pachet.creatii;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

interface SalNet {
    double salNet();
}

class Salariat implements Cloneable, SalNet, Serializable{
    private String nume;
    private String firma;
    private double salariu;
    private int bonus;

    public Salariat() {
    }



    public Salariat(String nume, String firma, double salariu, int bonus) {
        this.nume = nume;
        this.firma = firma;
        this.salariu = salariu;
        this.bonus = bonus;
    }

    public String getNume() {
        return nume;
    }

    public String getFirma() {
        return firma;
    }

    public double getSalariu() {
        return salariu;
    }

    public int getBonus() {
        return bonus;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public void setSalariu(double salariu) {
        this.salariu = salariu;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salariat client = (Salariat) o;
        return Double.compare(client.getSalariu(), getSalariu()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSalariu());
    }

    @Override
    public String toString() {
        return "Salariat{" +
                "nume='" + nume + '\'' +
                ", firma='" + firma + '\'' +
                ", salariu=" + salariu +
                ", bonus=" + bonus +
                '}';
    }

    public int compareTo(Salariat s) {
        return nume.compareTo(s.nume);
    }

    public int compareTo(String anotherString) {
        return firma.compareTo(anotherString);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public double salNet(){
        return salariu+bonus;
    }

    //Serializare
    public void serializare(){
        try {
            FileOutputStream fileOut = new FileOutputStream("salariat.bin");
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(this);
            objOut.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    //Deserializare
    public static Salariat deserializare() throws IOException, ClassNotFoundException{
        FileInputStream fileIn=new FileInputStream("salariat.bin");
        ObjectInputStream objIn=new ObjectInputStream(fileIn);
        Salariat s = (Salariat) objIn.readObject();
        objIn.close();
        return s;
    }

}

class Firma{
    private String denumire;
    private List<Salariat> list = new ArrayList<>();

    public Firma() {
    }

    public Firma(List<Salariat> list) {
        this.list = list;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public List<Salariat> getList() {
        return list;
    }

    public void setList(List<Salariat> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Firma{" +
                "denumire='" + denumire + '\'' +
                ", list=" + list +
                '}';
    }


}



public class Main {
    public static List<Salariat> listSal = new ArrayList<>();
    public static List<Firma> listFirme = new ArrayList<>();
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    public static void main(String[] args) {
        System.out.println("Scriere in csv sau text");
        try {
            //Scriere in fisier text sau csv
            //scriereinFisierDefault();

            Salariat s=new Salariat("Robert","AmericanGreetings",2000,800);
            //Implementare metoda clone
            Salariat s1=(Salariat) s.clone();
            System.out.println(s1);

            //Citire din fiser csv sau txt
           // citiredinFisierDefault();
            Salariat s2=citiredinFisierDefault(0);
            System.out.println(s2);

            //Creare lista
            listSal = creareLista();
            listSal.forEach(System.out::println);

            //Comparare 2 Salariati
            if((s1.getNume().compareTo(s2.getNume())<0)){
                System.out.println(s1);
            }
            else {
                System.out.println(s2);
            }

            //Afisare Salariu
            System.out.println(s2.salNet());
            System.out.println('\n');

            //Afisare Lista ordonata alfaetic
            List<Salariat> listaOrdonata=listSal.stream()
                    .sorted((s3,s4)-> s3.getNume().compareTo(s4.getNume()))
                    .collect(Collectors.toList());
            listaOrdonata.forEach(System.out::println);
            System.out.println('\n');

            //Afisa Lista triere dupa salarii
            List<Salariat> listaSalarii= listSal.stream()
                    .filter((s5)->(s5.getSalariu()>2000))
                    .collect(Collectors.toList());
            listaSalarii.forEach(System.out::println);
            System.out.println('\n');

            //Creare csv cu Firme
           // scriereCsv();

            //Selectarea oamenilor dintr-o companie
            String comp="InfoSyS";
            List<Salariat> listAng=listSal.stream()
                    .filter((s3)->(s3.getFirma().equals(comp)))
                    .collect(Collectors.toList());
            listAng.forEach(System.out::println);
            System.out.println('\n');

            //Set de Salaria
            Set<Salariat> salariatSet=listSal.stream().filter((salariat) -> (salariat.getSalariu()<3000)).collect(Collectors.toSet());
            salariatSet.forEach(System.out::println);
            System.out.println('\n');

            //Map


            //Scriere in binar un salariat
            scriereInBinar(s);
            System.out.println('\n');

            //Citire din fisier binar
            citireDinnBinar();
            System.out.println('\n');

            //Interactiunea Server-Side

            //Interactiunea Client-Side


        }
        catch (Exception ex){
            System.out.println(ex);
        }


    }

    public static void scriereinFisierDefault(){
        try {
            FileOutputStream fileOS = new FileOutputStream("salariat.csv");
            OutputStreamWriter streamW = new OutputStreamWriter(fileOS);
            BufferedWriter writer = new BufferedWriter(streamW);
            writer.write("Andrei");
            writer.write(",");
            writer.write("Delloite");
            writer.write(",");
            writer.write(2000);
            writer.write(",");
            writer.write(7000);
            writer.close();
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    public static Salariat  citiredinFisierDefault(int i) throws Exception{
        try(FileInputStream fileIS= new FileInputStream("salariat.csv");
        ) {
            InputStreamReader streamR = new InputStreamReader(fileIS);
            BufferedReader reader = new BufferedReader(streamR);
            String line="";
            int k=0;
            Salariat s=new Salariat();
            while ((line=reader.readLine())!=null) {
                String[] t = line.split(",");
               s.setNume(t[0]);
               s.setFirma(t[1]);
               s.setSalariu(Double.parseDouble(t[2]));
               s.setBonus(Integer.parseInt(t[3]));
               if(k==i) {
                   break;
               }
               k++;
            }
            return s;
        }
    }
    //Creare Lista
    public static List<Salariat> creareLista() throws Exception{
        try(BufferedReader in = new BufferedReader(new FileReader("salariat.csv"))) {
           return in.lines().map(linie->{
                String[] t = linie.split(",");
                Salariat s=new Salariat();
                s.setNume(t[0].trim());
                s.setFirma(t[1].trim());
                s.setSalariu(Double.parseDouble(t[2].trim()));
                s.setBonus(Integer.parseInt(t[3].trim()));
                return s;
            }).collect(Collectors.toList());
        }

    }

    //Scriere Csv cu Firme
    public  static void scriereCsv() throws Exception{
        int ok=0;
        try(PrintWriter out = new PrintWriter("Firme.csv")) {

                List<Salariat> listaFirma = listSal.stream().distinct().collect(Collectors.toList());
                for (Salariat s3 : listaFirma)
                    out.println(s3.getFirma());

            }

        }

    //Scriere in fis
    public static void scriereInBinar(Salariat s){
        try {
            FileOutputStream fileOut = new FileOutputStream("salariat.bin");
            DataOutputStream dataOut = new DataOutputStream(fileOut);
            dataOut.writeUTF(s.getNume());
            dataOut.writeUTF(s.getFirma());
            dataOut.writeDouble(s.getSalariu());
            dataOut.writeInt(s.getBonus());
            dataOut.close();

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    //Citire din fisier Binar
    public static void citireDinnBinar(){
        try {
            FileInputStream fileIn= new FileInputStream("salariat.bin");
            DataInputStream dataIn = new DataInputStream(fileIn);
            String nume=dataIn.readUTF();
            String firma=dataIn.readUTF();
            double salariu=dataIn.readDouble();
            int bonus=dataIn.readInt();
            Salariat s = new Salariat(nume,firma,salariu,bonus);
            System.out.println(s);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    //Scriere lista in fisier Binar
  /*  public static void listaBinar(List<Salariat> list){
        try {
            FileOutputStream fileOut = new FileOutputStream("lista.bin");
            DataOutputStream dataOut = new DataOutputStream(fileOut);
            for(int i=0;i<list.size();i++){
                dataOut.writeUTF(list)
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }*/

    }






