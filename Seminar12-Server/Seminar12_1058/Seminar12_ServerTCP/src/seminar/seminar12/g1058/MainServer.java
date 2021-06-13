package seminar.seminar12.g1058;

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
import java.util.List;
import java.util.stream.Collectors;

public class MainServer implements Closeable {
    private ServerSocket s;
    private boolean serverActiv;
    private List<Eveniment> evenimente;
    private SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy/HH");
    private SimpleDateFormat fmt1 = new SimpleDateFormat("dd.MM.yyyy");
    private SimpleDateFormat fmt2 = new SimpleDateFormat("HH");
    private DecimalFormat dfmt = new DecimalFormat("00");

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
                    Thread firTratareCerere = new Thread(()->tratareCerere(socket,c));
                    firTratareCerere.start();
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
                serverActiv=false;
                out.writeObject("Inchidere server.");
            }
            if (cod==2){
                String nume = in.readObject().toString();
                List<Eveniment> lista = evenimente.stream()
                        .filter(eveniment -> eveniment.getNumeSolicitant().contains(nume))
                        .collect(Collectors.toList());
                out.writeObject(lista);
            }
            if (cod==1){
                Eveniment eveniment = (Eveniment) in.readObject();
                evenimente.add(eveniment);
                try(Statement s = c.createStatement()){
                    String cmd = "insert into EVENIMENTE values('"+
                            eveniment.getNumeSolicitant()+"','"+
                            fmt1.format(eveniment.getData())+"',"+
                            fmt2.format(eveniment.getData())+","+
                            eveniment.getNumarLocuri()+
                            ")";
                    s.executeUpdate(cmd);
                }
                out.writeObject("Adaugare efectuata.");
            }
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }

    private void citireDate(Connection c) throws Exception{
        try(Statement s = c.createStatement(); ResultSet r = s.executeQuery("select * from EVENIMENTE")){
            evenimente = new ArrayList<>();
            while (r.next()){
                Eveniment eveniment = new Eveniment();
                eveniment.setNumeSolicitant(r.getString(1));
                String data = r.getString(2);
                int ora = r.getInt(3);
                eveniment.setData(fmt.parse(data+"/"+dfmt.format(ora)));
                eveniment.setNumarLocuri(r.getInt(4));
                evenimente.add(eveniment);
            }

        }
    }

    @Override
    public void close() throws IOException {
        if(s!=null){
            try{
                s.close();
            }
            catch (IOException ex){
                System.err.println(ex);
            }
        }
    }
}
