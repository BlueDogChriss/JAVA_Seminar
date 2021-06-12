package seminar.seminar5.g1058;



import seminar.seminar4.g1058.Depozit;
import seminar.seminar4.g1058.MainSeminar4;
import seminar.seminar4.g1058.Moneda;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
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

        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }
}
