package seminar.seminar10.g1050;

import seminar.seminar4.g1050.Carte;
import seminar.seminar4.g1050.Domeniu;
import seminar.seminar4.g1050.MainSeminar4;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class Main implements Closeable {
    private Connection c;

    public Main() throws Exception{
        String urlConectare = "jdbc:sqlite:seminar10_1050.db";
        c = DriverManager.getConnection(urlConectare);
    }

    public static void main(String[] args) {
        try(Main app = new Main()){
            System.out.println("Conexiune creata ...");
            MainSeminar4 mainSeminar4 = new MainSeminar4();
            mainSeminar4.citire("carti.csv");
            mainSeminar4.creareLista();
            List<Carte> lista = mainSeminar4.getListaCarti();
//            lista.forEach(System.out::println);
            app.salvare(lista);
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    private void salvare(List<Carte> lista) throws Exception{
//        Verificare existenta tabela CARTE
        try(ResultSet r = c.getMetaData().getTables(null,null,"CARTE",new String[]{"TABLE"})){
            if (!r.next()){
                try(Statement s = c.createStatement()){
                    String comandaCreareTabela = "create table CARTE ("+
                            "cota varchar(10),"+
                            "titlu varchar(100),"+
                            "autori varchar(200),"+
                            "editura varchar(50),"+
                            "nr_pagini integer,"+
                            "an_aparitie integer,"+
                            "valoare_inventar double,"+
                            "domenii varchar(50),primary key (cota)"+
                            ")";
                    s.executeUpdate(comandaCreareTabela);
                    System.out.println("Tabela CARTE a fost creata.");
                }
            } else {
                try(Statement s = c.createStatement()){
                    s.executeUpdate("delete from CARTE");
                }
            }
        }
//        Adaugare inregistrari din lista
        try(Statement s = c.createStatement()){
            for (Carte carte:lista){
                String t = Arrays.toString(carte.getAutori());
                String sAutori = t.substring(1,t.length()-1);
                StringBuilder sDomenii = new StringBuilder();
                Domeniu[] domenii = carte.getDomenii();
                for (int i=0;i<domenii.length-1;i++){
                    sDomenii.append(domenii[i]).append(",");
                }
                sDomenii.append(domenii[domenii.length-1]);
                String comandaInserare = "insert into CARTE values('"+
                        carte.getCota()+"','"+
                        carte.getTitlu()+"','"+
                        sAutori+"','"+
                        carte.getEditura()+"',"+
                        carte.getNrPagini()+","+
                        carte.getAnAparitie()+","+
                        carte.getValoareInventar()+",'"+
                        sDomenii+"'"+
                        ")";
                System.out.println(comandaInserare);
                s.executeUpdate(comandaInserare);
            }
        }
        System.out.println("Inserarea efectuata cu succes.");
    }

    @Override
    public void close() throws IOException {
        try{
            if (c!=null){
                c.close();
            }
        }
        catch (SQLException ex){
            System.err.println(ex);
        }
    }
}
