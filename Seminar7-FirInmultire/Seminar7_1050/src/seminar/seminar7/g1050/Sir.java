package seminar.seminar7.g1050;

public class Sir<T extends Comparable<T>> {
    protected T[] x;

    public Sir() {
    }

    public Sir(T[] x) {
        this.x = x;
    }

    public T[] getX() {
        return x;
    }

    public void setX(T[] x) {
        this.x = x;
    }
}
