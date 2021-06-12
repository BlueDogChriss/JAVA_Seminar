package seminar.seminar5.g1050;



import seminar.seminar4.g1050.Carte;
import seminar.seminar4.g1050.MainSeminar4;
import seminar.seminar4.g1050.Sala;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
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
            lista.forEach( carte ->  System.out.println(carte));
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
                    filter(carte -> carte.getSalaRezervare()!=null || carte.getDataReturnare()!=null).
                    collect(Collectors.toList());

            System.out.println("Carti imprumutate sau rezervate: ");
            cartiImprumutateRezervate.forEach(carte -> System.out.println(carte));

//            Cerinta 2
            List<Carte> cartiAutor = lista.stream().
                    filter(carte -> Arrays.stream(carte.getAutori()).
                            anyMatch( autor->autor.equalsIgnoreCase("Popescu Ion") ) ).
                    collect(Collectors.toList());
            System.out.println("Cartile autorului Popescu Ion:");
            cartiAutor.forEach(carte -> System.out.println(carte));

        }
        catch (Exception ex){
            System.err.println();
        }
    }
}
