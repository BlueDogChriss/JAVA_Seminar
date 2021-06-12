package seminar.seminar12.g1050;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainServer implements Closeable {
    private ServerSocket s;
    private List<Examen> examene;
    private SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy/HH");
    private SimpleDateFormat fmt1 = new SimpleDateFormat("dd.MM.yyyy");
    private SimpleDateFormat fmt2 = new SimpleDateFormat("HH");
    private DecimalFormat dfmt = new DecimalFormat("00");
    private boolean serverActiv;

    public static void main(String[] args) {
        try(MainServer app = new MainServer()){
            app.start();
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    private void start() throws Exception{
        try(Connection c = DriverManager.getConnection("jdbc:sqlite:seminar12.db")){
            citireDate(c);
            s = new ServerSocket(2000);
            serverActiv = true;
            s.setSoTimeout(10000);
            System.out.println("Server startat ...");
            while (serverActiv){
                try{
                    Socket socket = s.accept();
                    Thread firPrelucrare = new Thread(()->tratareCerere(socket,c));
                    firPrelucrare.start();
                }
                catch (Exception ex){}
            }
        }
    }

    private void tratareCerere(Socket socket,Connection c){
        try(ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())){
            int cod = (Integer) in.readObject();
            if (cod==3){
                serverActiv = false;
                out.writeObject("Server inchis.");
            }
            if (cod==1){
                Examen examen = (Examen) in.readObject();
                examene.add(examen);
                try(Statement s = c.createStatement()){
                    String cmdInsert = "insert into EXAMENE values('"+
                            examen.getTitular()+"','"+
                            fmt1.format(examen.getData())+"',"+
                            fmt2.format(examen.getData())+","+
                            examen.getNumarStudenti()+
                            ")";
                    s.executeUpdate(cmdInsert);
                }
                out.writeObject("Examen adaugat.");
            }
            if(cod==2){
                Date data = (Date)in.readObject();
                boolean sters = examene.remove(new Examen(data));
                if (sters){
                    try(Statement s = c.createStatement()){
                        String cmd = "delete from EXAMENE where data='"+fmt1.format(data)+"' and ora="+fmt2.format(data);
                        s.executeUpdate(cmd);
                        out.writeObject("Examen sters.");
                    }
                } else {
                    out.writeObject("Nu exista un examen la data de "+fmt.format(data));
                }
            }
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    private void citireDate(Connection c) throws Exception{
        examene = new ArrayList<>();
        try(Statement s = c.createStatement(); ResultSet r = s.executeQuery("select * from EXAMENE")){
            while (r.next()){
                Examen examen = new Examen();
                examen.setTitular(r.getString(1));
                String data = r.getString(2);
                int ora = r.getInt(3);
                examen.setData(fmt.parse(data+"/"+dfmt.format(ora)));
                examen.setNumarStudenti(r.getInt(4));
                examene.add(examen);
            }
        }
    }

    @Override
    public void close() throws IOException {
        if(s!=null){
            try{
                s.close();
            }
            catch (Exception ex){
                System.err.println(ex);
            }
        }
    }
}
