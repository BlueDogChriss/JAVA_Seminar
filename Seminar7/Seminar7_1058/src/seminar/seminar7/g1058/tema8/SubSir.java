package seminar.seminar7.g1058.tema8;

public class SubSir<T extends Comparable<T>> extends Sir<T> implements Runnable{
    private int p,u;

    public SubSir(T[] x, int p, int u) {
        super(x);
        this.p = p;
        this.u = u;
    }

    @Override
    public void run() {
    }
}
