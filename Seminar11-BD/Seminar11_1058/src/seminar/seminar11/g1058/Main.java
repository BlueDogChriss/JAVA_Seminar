package seminar.seminar11.g1058;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main implements Closeable {
    private Connection c;

    public Main() throws Exception {
        c = DriverManager.getConnection("jdbc:sqlite:seminar11.db");
    }

    public static void main(String[] args) {
        try(Main app = new Main()){
            System.out.println("Conexiune creata ...");
//            Cerinte
//            Cerinta 1. De completat denumire dupa contul creditor
            List<Jurnal> jurnalInregistrari = app.citireInregistrari();
            System.out.println("Jurnalul de inregistrari:");
            jurnalInregistrari.forEach(System.out::println);
//            Cerinta 2
            List<Jurnal> listaSortata = jurnalInregistrari.stream().sorted((i1,i2)->{
                if (i1.getSuma()<i2.getSuma()){
                    return 1;
                } else {
                    return -1;
                }
            }).collect(Collectors.toList());
            System.out.println("Jurnalul de inregistrari sortate dupa sume:");
            listaSortata.forEach(System.out::println);
//          Cerinta 3
            int simbol = 401;
            List<Jurnal> fisa = jurnalInregistrari.stream().
                    filter(i -> i.getContd().getCod()==simbol||i.getContc().getCod()==simbol)
                    .collect(Collectors.toList());
            System.out.println("Fisa "+simbol+":");
            fisa.forEach(System.out::println);

//            Cerinta 4
            app.stergere(simbol);
            app.modificare(5121,5124);
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    private void stergere(int simbol) throws Exception{
        try(Statement s = c.createStatement()){
            s.executeUpdate("delete from CONTURI where cod="+simbol);
            s.executeUpdate("delete from JURNAL where contd="+simbol+" or contc="+simbol);
        }
    }

    private void modificare(int simbolVechi,int simbolNou) throws Exception{
        try(Statement s = c.createStatement()){
            s.executeUpdate("update CONTURI set cod="+simbolNou+" where cod="+simbolVechi);
            s.executeUpdate("update JURNAL set contd="+simbolNou+" where contd="+simbolVechi);
            s.executeUpdate("update JURNAL set contc="+simbolNou+" where contc="+simbolVechi);
        }
    }


    private List<Jurnal> citireInregistrari() throws Exception{
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
        List<Jurnal> lista = new ArrayList<>();
//        Se foloseste o lista de conturi pentru a nu instantia mai multe obiecte Cont cu acelasi simbol
        List<Cont> conturi = new ArrayList<>();
        try(Statement s = c.createStatement()) {
            String comanda = "select * from JURNAL,CONTURI as C1,CONTURI as C2 where" +
                    " JURNAL.contd=C1.cod and JURNAL.contc=C2.cod";
            try (ResultSet r = s.executeQuery(comanda)) {
                while (r.next()) {
//                    for (int i=1;i<m;i++){
//                        System.out.print(r.getObject(i)+",");
//                    }
//                    System.out.println(r.getObject(m));
                    Jurnal jurnal = new Jurnal();
                    int simbolContD = r.getInt(2);
                    Cont contD = cautare(conturi,simbolContD);
                    int simbolContC = r.getInt(3);
                    Cont contC = cautare(conturi,simbolContC);
                    jurnal.setDataI(fmt.parse(r.getString(4)));
                    jurnal.setSuma(r.getDouble(5));
                    contD.setDenumire(r.getString(7));
                    contC.setDenumire(r.getString(9));
                    jurnal.setContc(contC);
                    jurnal.setContd(contD);
                    lista.add(jurnal);
                }
            }
        }
        return lista;
    }

//    Metoda care cauta un cont cu simbolul dat in lista de conturi
    private Cont cautare(List<Cont> conturi,int simbol){
        Cont cont = new Cont(simbol);
        int k = conturi.indexOf(cont);
        if (k==-1){
            conturi.add(cont);
        } else {
            cont = conturi.get(k);
        }
        return cont;
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
