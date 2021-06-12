package seminar.seminar7.g1058.tema8;

public class Sir<T extends Comparable<T>> {
    protected T[] x;

    public Sir(T[] x) {
        this.x = x;
    }

    public T[] getX() {
        return x;
    }
}
