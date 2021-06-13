package seminar.seminar5.g1058;



import seminar.seminar4.g1058.Depozit;
import seminar.seminar4.g1058.MainSeminar4;
import seminar.seminar4.g1058.Moneda;
import seminar.seminar4.g1058.Titular;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try{
            MainSeminar4 seminar4 = new MainSeminar4();
            seminar4.citire("depozite.csv");
            seminar4.creareLista();
//            seminar4.afisareLista("Lista de depozite:");
            List<Depozit> lista = seminar4.getListaDepozite();
            System.out.println("Lista depozitelor:");
//            lista.forEach(new Consumer<Depozit>() {
//                @Override
//                public void accept(Depozit depozit) {
//                    System.out.println(depozit);
//                }
//            });
            lista.forEach(depozit -> System.out.println(depozit));
//            lista.forEach(System.out::println);

//            Cerinta 1
            String persoana = "Ionescu Diana";
//            List<Depozit> depozitePersoana = lista.stream().filter(new Predicate<Depozit>() {
//                @Override
//                public boolean test(Depozit depozit) {
//                    return depozit.getTitular().getNume().equalsIgnoreCase(persoana);
//                }
//            }).collect(Collectors.toList());
            List<Depozit> depozitePersoana = lista.stream().
                    filter( depozit -> depozit.getTitular().getNume().equalsIgnoreCase(persoana)).
                    collect(Collectors.toList());
            System.out.println("Lista depozitelor titularului "+persoana+":");
            depozitePersoana.forEach(depozit -> System.out.println(depozit));

//            Cerinta 2
            Moneda moneda = Moneda.EURO;
            List<Depozit> depoziteMoneda = lista.stream()
                    .filter( depozit -> depozit.getMoneda().equals(moneda) )
                    .collect(Collectors.toList());
            System.out.println("Depozite in "+moneda.name()+":");
            depoziteMoneda.forEach(System.out::println);

//            Cerinta 3
            SimpleDateFormat fmt= new SimpleDateFormat("dd.MM.yyyy");
            Date d1 = fmt.parse("01.02.2021"),d2 = fmt.parse("01.03.2021");
            List<Depozit> depoziteInterval=lista.stream().filter(depozit -> depozit.getDataDeschidere().after(d1) && depozit.getDataDeschidere().before(d2))
                    .collect(Collectors.toList());
            System.out.println("depozite deschise intre "+fmt.format(d1)+" si "+fmt.format(d2)+":");
            depoziteInterval.forEach(System.out::println);

//            Cerinta 4
            List<Depozit> depoziteSortateDupaData = lista.stream()
                    .sorted((depozit1,depozit2) -> -depozit1.getDataDeschidere().compareTo(depozit2.getDataDeschidere()))
                    .collect(Collectors.toList());
            System.out.println("Lista depozitelor sortate invers dupa data deschiderii:");
            depoziteSortateDupaData.forEach(System.out::println);

//            Cerinta 5
            Set<Titular> lista5 = lista.stream().map(depozit -> depozit.getTitular())
                    .collect(Collectors.toSet());
            System.out.println("Lista titularilor:");
            lista5.forEach(System.out::println);

//            Cerinta 6
            Map<Integer,Titular> lista6 = lista.stream().collect(
                    Collectors.toMap(
                            depozit -> depozit.getCodContract(),
                            Depozit::getTitular)
            );
            System.out.println("Grupare titulari pe contracte:");
            lista6.keySet().forEach(codContract ->
                    System.out.println(codContract+":"+lista6.get(codContract)));

//            Cerinta 7
            Map<Long,List<Depozit>> lista7 = lista.stream().collect(
                    Collectors.groupingBy(depozit -> depozit.getTitular().getCnp())
            );
            System.out.println("Colectare depozite pe cnp titular:");
            lista7.keySet().forEach(cnp -> {
                System.out.println("Lista depozite pentru Cnp="+cnp+":");
                lista7.get(cnp).forEach(System.out::println);
            });

//            Cerinta 8
            Map<Moneda,Double> lista8 = lista.stream()
                    .collect(Collectors.groupingBy(
                            depozit -> depozit.getMoneda(),
                            Collectors.averagingDouble(depozit -> depozit.getValoare())
                    ));
            Map<Moneda,Double> lista8_ = lista.stream()
                    .collect(Collectors.groupingBy(
                            depozit -> depozit.getMoneda(),
                            Collectors.summingDouble(depozit -> depozit.getValoare())
                    ));
            System.out.println("Valoare medie depozite pe valuta:");
            lista8.keySet().forEach(moneda_ ->
                    System.out.println(moneda_+":"+lista8.get(moneda_)));
            System.out.println("Valoare depozite pe valuta:");
            lista8_.keySet().forEach(moneda_ ->
                    System.out.println(moneda_+":"+lista8_.get(moneda_)));

//            Cerinta 9
            Map<Integer,?> lista9 = lista.stream().
                    collect(
                            Collectors.toMap(
                                    depozit -> depozit.getCodContract(),
                                    depozit -> new Object(){
                                        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyy");
                                        @Override
                                        public String toString() {
                                            return depozit.getTitular().getNume()+","
                                                    + fmt.format(depozit.getDataDeschidere());
                                        }
                                    }
                            )
                    );
            System.out.println("Colectare nume titular si data deschidere pe contract:");
            lista9.keySet().forEach(codContract -> {
                System.out.println("Cod contract: "+codContract);
                Object obiectAnonim = lista9.get(codContract);
                System.out.println("Nume titular si data deschidere:"+obiectAnonim);
            });
//            Cerinta 9 - Clasa locala
            class Info {
                String nume;
                Date data;
                SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");

                public Info(String nume, Date data) {
                    this.nume = nume;
                    this.data = data;
                }

                @Override
                public String toString() {
                    return nume + "," + fmt.format(data);
                }
            }
            Map<Integer,Info> lista9_1 = lista.stream().collect(
                    Collectors.toMap(
                            Depozit::getCodContract,
                            depozit -> new Info(depozit.getTitular().getNume(),depozit.getDataDeschidere())
                    )
            );
            System.out.println("Colectare nume titular si data deschidere pe contract:");
            lista9_1.keySet().forEach(codContract -> {
                System.out.println("Cod contract: "+codContract);
                Info info = lista9_1.get(codContract);
                System.out.println("Nume titular:"+info.nume);
                System.out.println("Data deschidere:"+info.data);
            });

//            Cerinta 10
            Set<String> lista10 = lista.stream().collect(
                    new Supplier<Set<String>>() {
                        @Override
                        public Set<String> get() {
                            return new HashSet<>();
                        }
                    },
                    new BiConsumer<Set<String>, Depozit>() {
                        @Override
                        public void accept(Set<String> lista, Depozit depozit) {
                            lista.add(depozit.getTitular().getAdresa().getLocalitate());
                            lista.add(depozit.getTitular().getAdresa().getStrada());
                        }
                    },
                    new BiConsumer<Set<String>, Set<String>>() {
                        @Override
                        public void accept(Set<String> listaFinala, Set<String> lista) {
                            listaFinala.addAll(lista);
                        }
                    }
            );
            Set<String> lista10_ = lista.stream().collect(
                    HashSet::new,
                    (lista_acumulare,depozit) -> {
                        lista_acumulare.add(depozit.getTitular().getAdresa().getLocalitate());
                        lista_acumulare.add(depozit.getTitular().getAdresa().getStrada());
                    },
                    HashSet::addAll
            );
            System.out.println("Colectare localitati si strazi:");
            lista10_.forEach(System.out::println);
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }
}
