package seminar.seminar7.g1050;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        try {
//            int n = 100;
//            Double[] x = new Double[n];
//            for (int i = 0; i < n; i++) {
//                x[i] = Math.random() * n;
//            }

            String[] x = new String[]{
                    "Popa Maria","Aldea Mihai","Popescu Ion",
                    "Ionescu Dan","Georgescu Liviu","Badea Alina"
            };
            int n = x.length;

//        Creare fire
            int m = n / 2;
//            SubSir<Double> s1 = new SubSir<>(x, 0, m - 1);
//            SubSir<Double> s2 = new SubSir<>(x, m, n - 1);

            SubSir<String> s1 = new SubSir<>(x, 0, m - 1);
            SubSir<String> s2 = new SubSir<>(x, m, n - 1);

            Thread f1 = new Thread(s1);
            Thread f2 = new Thread(s2);
//        Lansare fire
            f1.start();
            f2.start();
//        Sincronizare f1 si f2 cu main (firul principal)
            f1.join();
            f2.join();

//            s1.interclasare(m, n - 1, new Alocare<Double>() {
//                @Override
//                public Double[] aloc(int n) {
//                    return new Double[n];
//                }
//            });

//            s1.interclasare(m,n-1,nr -> new Double[nr]);
            s1.interclasare(m,n-1,nr -> new String[nr]);
//            afisare(x,"Vectorul sortat:");
            System.out.println("Sirul sortat:\n"+Arrays.toString(x));
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    public static void afisare(Double[] x,String mesaj){
        System.out.println(mesaj);
        for (double v:x){
            System.out.println(v);
        }
    }
}
