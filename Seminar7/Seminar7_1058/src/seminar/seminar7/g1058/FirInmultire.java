package seminar.seminar7.g1058;

public class FirInmultire extends Thread{
    private double[][] x,y,z;
    private int i1,i2,j1,j2;

    public FirInmultire(double[][] x, double[][] y, double[][] z, int i1, int i2, int j1, int j2) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.i1 = i1;
        this.i2 = i2;
        this.j1 = j1;
        this.j2 = j2;
    }

    @Override
    public void run() {
        for (int i=i1;i<=i2;i++){
            for (int j=j1;j<=j2;j++){
                z[i][j] = 0;
                for (int k=0;k<y.length;k++){
                    z[i][j]+=x[i][k]*y[k][j];
                }
            }
        }
    }
}
