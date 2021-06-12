package seminar.seminar1.g1058;

import java.util.Arrays;
import java.util.Scanner;

public class MainSeminar1 {

    public static void main(String[] args) {
//        Cerinta 1
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
        System.out.println(Arrays.toString(args));
        int n = Integer.parseInt(args[0]), m = Integer.parseInt(args[1]);
        int[][] x = new int[n][m];
        int k = n < m ? n : m;
        for (int i = 0; i < k; i++) {
            x[i][i] = 1;
        }
        afisareMatrice(x, "x:");
//        Cerinta 2
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = (int) (Math.random() * (n + m));
            }
        }
        afisareMatrice(a, "a:");
//       Cerinta 3
        Scanner scanner = new Scanner(System.in);
        int[] dimensiuni = new int[0];
        while (scanner.hasNext()){
            dimensiuni = add(dimensiuni,scanner.nextInt());
        }
        System.out.println(Arrays.toString(dimensiuni));
        int[][] y = new int[dimensiuni.length][];
        for (int i=0;i<dimensiuni.length;i++){
            y[i] = new int[dimensiuni[i]];
            for (int j=0;j<y[i].length;j++){
                y[i][j] = i;
            }
        }
        afisareMatrice(y,"y:");
    }

    private static int[] add(int[] dimensiuni,int valoareNoua){
        int n = dimensiuni.length;
        int[] y = Arrays.copyOf(dimensiuni,n+1);
        y[n] = valoareNoua;
        return y;
    }

    private static void afisareMatrice(int[][] x, String mesaj) {
        System.out.println(mesaj);
        for (int[] linie : x) {
            System.out.println(Arrays.toString(linie));
        }
    }
}

