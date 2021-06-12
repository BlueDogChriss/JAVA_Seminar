package seminar.seminar5.g1050;


import seminar.seminar4.g1050.Carte;
import seminar.seminar4.g1050.MainSeminar4;
import seminar.seminar4.g1050.Sala;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            MainSeminar4 seminar4 = new MainSeminar4();
            seminar4.citire("carti.csv");
            seminar4.creareLista();
            List<Carte> lista = seminar4.getListaCarti();
//            seminar4.afisareLista("Lista carti:");
//            Afisare elemente lista prin interfata Consumer
            System.out.println("Lista de carti:");
//            lista.forEach(new Consumer<Carte>() {
//                @Override
//                public void accept(Carte carte) {
//                    System.out.println(carte);
//                }
//            });
            lista.forEach(carte -> System.out.println(carte));
//            lista.forEach( System.out::println );
//            Tematica Seminar 5
//            Cerinta 1

            lista.get(0).rezervareSala(Sala.GRIGORE_MOISIL);
//            List<Carte> cartiImprumutateRezervate = lista.stream().filter(new Predicate<Carte>() {
//                @Override
//                public boolean test(Carte carte) {
//                    return carte.getSalaRezervare()!=null || carte.getDataReturnare()!=null;
//                }
//            }).collect(Collectors.toList());

            List<Carte> cartiImprumutateRezervate = lista.stream().
                    filter(carte -> carte.getSalaRezervare() != null || carte.getDataReturnare() != null).
                    collect(Collectors.toList());

            System.out.println("Carti imprumutate sau rezervate: ");
            cartiImprumutateRezervate.forEach(carte -> System.out.println(carte));

//            Cerinta 2
            List<Carte> cartiAutor = lista.stream().
                    filter(carte -> Arrays.stream(carte.getAutori()).
                            anyMatch(autor -> autor.equalsIgnoreCase("Popescu Ion"))).
                    collect(Collectors.toList());
            System.out.println("Cartile autorului Popescu Ion:");
            cartiAutor.forEach(carte -> System.out.println(carte));

//            Cerinta 3
            double valInv1 = 50, valInv2 = 200;
            List<Carte> lista3 = lista.stream()
                    .filter(carte -> carte.getValoareInventar() >= valInv1 && carte.getValoareInventar() <= valInv2)
                    .collect(Collectors.toList());
            System.out.println("Carti cu valoare in intervalul [" + valInv1 + "," + valInv2 + "]:");
            lista3.forEach(System.out::println);

//            Cerinta 4
            List<Carte> lista4 = lista.stream()
                    .sorted((carte1, carte2) -> carte1.getEditura().compareTo(carte2.getEditura()))
                    .collect(Collectors.toList());
            System.out.println("Lista cartilor sortata dupa editura:");
            lista4.forEach(System.out::println);

//            Cerinta 5
//            Set<String> lista5 = lista.stream().map(carte -> carte.getEditura())
//                    .collect(Collectors.toSet());
            Set<String> lista5 = lista.stream().map(Carte::getEditura)
                    .collect(Collectors.toSet());
            System.out.println("Lista editurilor:");
            lista5.forEach(System.out::println);

//            Cerinta 6
            Map<String, String[]> lista6 = lista.stream().collect(Collectors.toMap(
                    carte -> carte.getCota(), carte -> carte.getAutori()
            ));
            System.out.println("Lista autorilor pe cote:");
            lista6.keySet().forEach(cota -> System.out.println(cota + ":" + Arrays.toString(lista6.get(cota))));

//            Cerinta 7
            Map<String, List<Carte>> lista7 = lista.stream()
                    .collect(Collectors.groupingBy(carte -> carte.getEditura()));
            System.out.println("Grupare carti pe edituri:");
            lista7.keySet().forEach(editura -> {
                System.out.println("Editura " + editura + ":");
                lista7.get(editura).forEach(carte -> System.out.println(carte));
            });

//            Cerinta 8

            Map<String, Double> lista8 = lista.stream().collect(
                    Collectors.groupingBy(
                            Carte::getEditura,
                            Collectors.averagingDouble(carte -> carte.getValoareInventar())
                    )
            );
            System.out.println("Valori medii de inventar pe edituri:");
            lista8.keySet().forEach(editura -> System.out.println(editura + ":" + lista8.get(editura)));


//            Cerinta 9
            Map<String, ?> lista9 = lista.stream()
                    .collect(Collectors.toMap(
                            Carte::getCota,
                            carte -> new Object() {
                                @Override
                                public String toString() {
                                    return carte.getTitlu() + "," + carte.getAnAparitie();
                                }
                            }
                    ));
            System.out.println("Grupare informatii (titlu, an aparitie) pe cote:");
            lista9.keySet().forEach(cota ->
                    {
                        Object obiectAnonim = lista9.get(cota);
                        System.out.println(cota + ":" + obiectAnonim);
                    }
            );

//            Cerinta 10
            Set<String> lista10 = lista.stream().collect(
                    new Supplier<Set<String>>() {
                        @Override
                        public Set<String> get() {
                            return new HashSet<>();
                        }
                    },
                    new BiConsumer<Set<String>, Carte>() {
                        @Override
                        public void accept(Set<String> strings, Carte carte) {
                            String[] autori = carte.getAutori();
                            for (String autor:autori){
                                strings.add(autor);
                            }

                        }
                    },
                    new BiConsumer<Set<String>, Set<String>>() {
                        @Override
                        public void accept(Set<String> strings, Set<String> strings2) {
                            strings.addAll(strings2);
                        }
                    }
            );
            System.out.println("Lista de autori:");
            lista10.forEach(System.out::println);

        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
}
