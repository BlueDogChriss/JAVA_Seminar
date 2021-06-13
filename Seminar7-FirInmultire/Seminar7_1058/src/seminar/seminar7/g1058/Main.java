package seminar.seminar7.g1058;

import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        int n = 1200, m = 1000, p = 1100;
        double[][] x = new double[n][m], y = new double[m][p], z = new double[n][p];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                x[i][j] = 1;
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                y[i][j] = 1;
            }
        }
        int l = 3;

        System.out.println("Start pe "+l*l+" fire ...");
        long start = new Date().getTime();

//        Calcul numar linii si coloane pe bloc
        int ki = n / l, kj = p / l;
//        Alocare masiv fire de executie
        FirInmultire[][] fire = new FirInmultire[l][l];
//        Creare fire de executie
        for (int i = 0; i < l; i++) {
            int i1 = i * ki, i2 = (i + 1) * ki - 1;
            if (i == l - 1) {
                i2 = n - 1;
            }
            for (int j = 0; j < l; j++) {
                int j1 = j * kj, j2 = (j + 1) * kj - 1;
                if (j == l - 1) {
                    j2 = p - 1;
                }
                fire[i][j] = new FirInmultire(x, y, z, i1, i2, j1, j2);
                fire[i][j].start();
            }
        }
//        Sincronizare fire cu main
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                try {
                    fire[i][j].join();
                } catch (InterruptedException ex) {
                    System.err.println(ex);
                }
            }
        }

        long stop = new Date().getTime();
        System.out.println("Inmultire pe " + l * l + " fire in " + (stop - start) + " milisecunde.");

        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException ex){
        }

        System.out.println("Start pe un fir ...");
        start = new Date().getTime();

        FirInmultire firInmultire = new FirInmultire(x,y,z,0,n-1,0,p-1);
        firInmultire.start();
        try {
            firInmultire.join();
        }
        catch (InterruptedException ex){
            System.err.println(ex);
        }
        stop = new Date().getTime();
        System.out.println("Inmultire pe un fir in " + (stop - start) + " milisecunde.");

//        print(z,"Z:");
    }

    public static void print(double[][] x, String mesaj) {
        System.out.println(mesaj);
        for (double[] linie : x) {
            System.out.println(Arrays.toString(linie));
        }
    }

}
