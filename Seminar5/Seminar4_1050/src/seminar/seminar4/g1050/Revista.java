package seminar.seminar4.g1050;

public class Revista extends Publicatie{
    private String colectia;
    private int aparitiiAn;

    public Revista() {

    }
    public Revista(String titlu, String cota,String editura, int anAparitie, double valoareInventar,
                   String colectia, int aparitiiAn) throws Exception {
        super(titlu, cota,editura, anAparitie, valoareInventar);
        this.colectia = colectia;
        this.aparitiiAn = aparitiiAn;
    }

    public String getColectia() {
        return colectia;
    }

    public void setColectia(String colectia) {
        this.colectia = colectia;
    }

    public int getAparitiiAn() {
        return aparitiiAn;
    }

    public void setAparitiiAn(int aparitiiAn) {
        this.aparitiiAn = aparitiiAn;
    }

    @Override
    public String toString() {
        return super.toString()+"\nRevista{" + colectia + "," + aparitiiAn+"}";
    }
}
