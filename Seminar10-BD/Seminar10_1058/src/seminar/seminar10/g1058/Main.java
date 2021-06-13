package seminar.seminar10.g1058;

import seminar.seminar4.g1058.Depozit;
import seminar.seminar4.g1058.MainSeminar4;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class Main implements Closeable {
    private Connection c;

    public Main() throws Exception{
        String urlConectare = "jdbc:sqlite:seminar10_1058.db";
        c = DriverManager.getConnection(urlConectare);
    }

    public static void main(String[] args) {
        try(Main app = new Main()){
            System.out.println("Conexiune creata ...");
            MainSeminar4 mainSeminar4 = new MainSeminar4();
            mainSeminar4.citire("depozite.csv");
            mainSeminar4.creareLista();
            List<Depozit> lista = mainSeminar4.getListaDepozite();
            lista.forEach(System.out::println);
            app.creareTabela(lista);
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    private void creareTabela(List<Depozit> lista) throws Exception{
//        Test existenta tabela
        try(ResultSet r = c.getMetaData().getTables(null,null,"DEPOZIT",new String[]{"TABLE"})){
            if(!r.next()){
                try(Statement s = c.createStatement()){
                    String comandaCreareTabela = "create table DEPOZIT ("+
                            "cod_contract integer,"+
                            "cnp_titular bigint,"+
                            "tip_depozit varchar(10),"+
                            "moneda varchar(10),"+
                            "sucursala varchar(50),"+
                            "data_deschidere varchar(10),"+
                            "valoare double,"+
                            "dobanda double"+
                            ")";
                    s.executeUpdate(comandaCreareTabela);
                    System.out.println("Tabela DEPOZIT a fost creata!");
                }
            } else {
                try(Statement s = c.createStatement()){
                    s.executeUpdate("delete from DEPOZIT");
                }
            }
//            DE COMPLETAT INSERAREA ARTICOLELOR

        }
    }

    @Override
    public void close() throws IOException {
        try{
            if(c!=null){
                c.close();
            }
        }
        catch (SQLException ex){
            System.err.println(ex);
        }
    }
}
