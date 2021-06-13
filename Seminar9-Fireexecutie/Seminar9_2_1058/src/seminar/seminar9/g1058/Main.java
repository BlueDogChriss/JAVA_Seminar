package seminar.seminar9.g1058;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        try(PrintWriter out = new PrintWriter("out.txt")){
            Profesor profesor = new Profesor(11,"Popescu Mihai",45,5000,
                    new Departament("Statistica","CSIE","Ionescu Maria"));
            writeObject(profesor,out);
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
        if( (linie = in.readLine())!=null ){
            String numeClasa = linie.trim();
            Class clasa = Class.forName(numeClasa);
            Object obiectClasa = clasa.getConstructor().newInstance();
            int numarCampuri = Integer.parseInt(in.readLine().trim());
            for (int i=0;i<numarCampuri;i++){
                String numeCamp = in.readLine().trim();
                Field camp = clasa.getDeclaredField(numeCamp);
                String tip = camp.getType().getSimpleName();
                switch (tip){
                    case "int":
                        int valoareInt = Integer.parseInt(in.readLine().trim());
                        Method metodaSet = clasa.getMethod("set"+numeCamp.substring(0,1).toUpperCase()+
                                numeCamp.substring(1),int.class);
                        metodaSet.invoke(obiectClasa,valoareInt);
                        break;
                    case "double":
                        double valoareD = Double.parseDouble(in.readLine().trim());
                        Method metodaSetD = clasa.getMethod("set"+numeCamp.substring(0,1).toUpperCase()+
                                numeCamp.substring(1),double.class);
                        metodaSetD.invoke(obiectClasa,valoareD);
                        break;
                    case "String":
                        String valoareS = in.readLine().trim();
                        Method metodaSetS = clasa.getMethod("set"+numeCamp.substring(0,1).toUpperCase()+
                                numeCamp.substring(1),String.class);
                        metodaSetS.invoke(obiectClasa,valoareS);
                        break;
                    default:
                        Object valoare = readObject(in);
                        Method metodaSetO = clasa.getMethod("set"+numeCamp.substring(0,1).toUpperCase()+
                                numeCamp.substring(1),valoare.getClass());
                        metodaSetO.invoke(obiectClasa,valoare);
                }
            }
            return obiectClasa;
        } else {
            return null;
        }
    }

    private static void writeObject(Object o,PrintWriter out) throws Exception{
        Class clasa = o.getClass();
        out.println(clasa.getName());
        Field[] campuri = clasa.getDeclaredFields();
        int nrCampuri = campuri.length;
        if (nrCampuri>0){
            out.println(nrCampuri);
            for (Field camp:campuri){
                String tip = camp.getType().getSimpleName();
                String numeCamp = camp.getName();
                out.println(numeCamp);
                Method metodaGet = clasa.getMethod("get"+numeCamp.substring(0,1).toUpperCase()+
                        numeCamp.substring(1));
                Object valoare = metodaGet.invoke(o);
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
