package seminar.seminar7.g1050;

public class SubSir<T extends Comparable<T>> extends Sir<T> implements Runnable {

    private int p,u;

    public SubSir() {
    }

    public SubSir(T[] x, int p, int u) {
        super(x);
        this.p = p;
        this.u = u;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getU() {
        return u;
    }

    public void setU(int u) {
        this.u = u;
    }

    @Override
    public void run() {
        sort();
    }

    private void sort(){
        int t=u,nt;
        do{
            nt=p;
            for (int i=p;i<t;i++){
                if(x[i].compareTo(x[i+1])>0){
                    T tmp = x[i];
                    x[i] = x[i+1];x[i+1]=tmp;
                    nt=i;
                }
            }
            t=nt;
        }
        while (t!=p);
    }

    public void interclasare(int p1,int u1,Alocare<T> alocare){
        int i=p,j=p1,k=0;
        int n1 = u-p+1,n2 = u1-p1+1;
        T[] swap = alocare.aloc(n1+n2);
        while (i<=u&&j<=u1){
            if (x[i].compareTo(x[j])<0){
                swap[k] = x[i];i++;
            } else {
                swap[k] = x[j];j++;
            }
            k++;
        }
        if (i<=u){
            for (int l=i;l<=u;l++,k++){
                swap[k] = x[l];
            }
        } else {
            for (int l=j;l<=u1;l++,k++){
                swap[k] = x[l];
            }
        }
        k = 0;
        for (int l=p;l<=u;l++,k++){
            x[l] = swap[k];
        }
        for (int l=p1;l<=u1;l++,k++){
            x[l] = swap[k];
        }
    }

}
