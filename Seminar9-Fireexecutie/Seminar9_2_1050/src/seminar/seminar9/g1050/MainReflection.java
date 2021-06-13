package seminar.seminar9.g1050;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) {
        try(PrintWriter out = new PrintWriter("out.txt")){
            Student student = new Student(10,1050,2,"Alexe Maria",9.50,
                    new Adresa("Brasov","Brasov","Calea Bucuresti","2a"));
            writeObject(student,out);
        }
        catch (Exception ex){
            System.err.println(ex);
        }
        try(BufferedReader in = new BufferedReader(new FileReader("out.txt"))){
            Object obiectCitit = readObject(in);
            System.out.println(obiectCitit);
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    private static Object readObject(BufferedReader in) throws Exception{
        String linie;
        if ( (linie=in.readLine())!=null ){
            String numeClasa = linie.trim();
            Class clasa = Class.forName(numeClasa);
            Object obiectClasa = clasa.getConstructor().newInstance();
//            De continuat
        }
        return null;
    }

    private static void writeObject(Object o,PrintWriter out) throws Exception{
        Class clasa = o.getClass();
        out.println(clasa.getName());
        Field[] campuri = clasa.getDeclaredFields();
        int nrCampuri = campuri.length;
        if ( nrCampuri>0 ){
            out.println(nrCampuri);
            for (Field camp:campuri){
                String tip = camp.getType().getSimpleName();
                String numeCamp = camp.getName();
                Method metodaGet = clasa.getMethod("get"+numeCamp.substring(0,1).toUpperCase()+
                        numeCamp.substring(1));
                Object valoare = metodaGet.invoke(o);
                out.println(numeCamp);
                switch (tip){
                    case "int":
                    case "double":
                    case "String":
                        out.println(valoare);
                        break;
                    default:
                        writeObject(valoare,out);
                }
            }
        }
    }
}
