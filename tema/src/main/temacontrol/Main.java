package main.temacontrol;


//Tema 7
//În fișierul io.csv sunt salvate tranzacțiile efectuate pe stocuri la luna curentă.
//Structura fișierului:
//idTranzactie,data,tipTranzactie,codStoc,cantitate
//Datele vor fi citite într-o listă de tip List<Tranzactie> unde Trancatie este clasa utilizată pentru a
//memora o tranzacție.
//Câmpuri Tranzactie:
//idTranzacție - cod unic de identificare tranzacție (int)
//data - data tranzacției (Date)
//tipTranzacție - String. Poate fi "I" sau "E" pentru intrare sau ieșire pe sau de pe stoc
//codStoc - codul stocului modificat (String).
//cantitate - cantitatea intrată/ieșită (double)
//Să se scrie o aplicație care să determine:
//- sortarea tranzacțiilor după cantitatea tranzacționată
//- selecția tranzacțiilor de tip intrare pentru un anumit stoc
//- selecția tranzacțiilor de tip ieșire pentru un anumit stoc
//- lista de tranzacții înregistrate într-o anumită perioadă
//- gruparea tranzacțiilor după data înregistrării într-o structură de tip Map<Date,List<Tranzactie>>
//- calcul intrări/ieșiri pe fiecare stoc într-o structură de tip Map<String,Double>, unde cheile de tip
//String sunt codurile stocurilor iar valorile reprezintă cantitățile
//Notă. Vor fi folosite facilitățile oferite de prelucrările funcționale ale colecțiilor.


public class Main {
}
