package seminar.seminar11.g1050;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main implements Closeable {
    private Connection c;

    public Main() throws Exception {
        c = DriverManager.getConnection("jdbc:sqlite:seminar11.db");
    }

    public static void main(String[] args) {
        try(Main app = new Main()){
            System.out.println("Conexiune creata ...");
            List<Proiect> proiecte = app.citireProiecte();
            System.out.println("Lista de proiecte:");
            proiecte.forEach(System.out::println);
//            Cerintele 2 si 3
            List<Proiect> proiecteSortate = proiecte.stream()
                    .sorted((p1,p2)->Double.compare(p2.getValoare(),p1.getValoare()))
                    .collect(Collectors.toList());
            System.out.println("Lista de proiecte sortate descrescator dupa valoare:");
            proiecteSortate.forEach(System.out::println);
            Map<String,Double> valoareProiecte = proiecte.stream().
                    collect(Collectors.groupingBy(p->p.getTitular().getDepartament(),Collectors.summingDouble(p->p.getValoare())));
            System.out.println("Valoare proiecte pe departamente:");
            valoareProiecte.keySet().forEach(d->System.out.println(d+","+valoareProiecte.get(d)));

//            Cerinta 4
            app.stergere(1680606458963L);
            proiecte = app.citireProiecte();
            System.out.println("Lista de proiecte dupa stergere:");
            proiecte.forEach(System.out::println);
            app.modificare(2651010456789L,2621010890765L);
            proiecte = app.citireProiecte();
            System.out.println("Lista de proiecte dupa modificare:");
            proiecte.forEach(System.out::println);
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    private void stergere(long cnp) throws Exception{
        try(Statement s = c.createStatement()){
            String comanda = "delete from TITULARI where cnp="+cnp;
            s.executeUpdate(comanda);
            String comanda2 = "delete from PROIECTE where cnp_director="+cnp;
            s.executeUpdate(comanda2);
        }
    }

    private void modificare(long cnpVechi,long cnpNou) throws Exception{
        try(Statement s = c.createStatement()){
            String comanda1 = "update TITULARI set cnp="+cnpNou+" where cnp="+cnpVechi;
            s.executeUpdate(comanda1);
            String comanda2 = "update PROIECTE set cnp_director="+cnpNou+" where cnp_director="+cnpVechi;
            s.executeUpdate(comanda2);
        }
    }

    private List<Proiect> citireProiecte() throws Exception{
        List<Proiect> lista = new ArrayList<>();
        List<Titular> titulari = new ArrayList<>();
        try(Statement s = c.createStatement()) {
            String comanda = "select * from PROIECTE,TITULARI where PROIECTE.cnp_director=TITULARI.cnp";
            try(ResultSet r = s.executeQuery(comanda)){
//                ResultSetMetaData rm = r.getMetaData();
//                int m = rm.getColumnCount();
//                for (int i=1;i<m;i++){
//                    System.out.print(rm.getColumnName(i)+",");
//                }
//                System.out.println(rm.getColumnName(m));
                while (r.next()){
//                    for (int i=1;i<m;i++){
//                        System.out.print(r.getObject(i)+",");
//                    }
//                    System.out.println(r.getObject(m));
                    Proiect proiect = new Proiect();
                    proiect.setAcronim(r.getString(1));
                    long cnp = r.getLong(2);
                    proiect.setFinantator(r.getString(3));
                    proiect.setValoare(r.getDouble(4));
                    String numeTitular = r.getString(6);
                    String departament = r.getString(7);

                    Titular titular = new Titular(cnp);
                    int k = titulari.indexOf(titular);
                    if (k!=-1){
                        titular = titulari.get(k);
                    } else {
                        titular.setDepartament(departament);
                        titular.setNume(numeTitular);
                        titulari.add(titular);
                    }
                    proiect.setTitular(titular);
                    lista.add(proiect);
                }
            }
        }
        return lista;
    }

    @Override
    public void close() throws IOException {
        if (c!=null){
            try{
                c.close();
            }
            catch (SQLException ex){
                System.err.println(ex);
            }
        }
    }
}
