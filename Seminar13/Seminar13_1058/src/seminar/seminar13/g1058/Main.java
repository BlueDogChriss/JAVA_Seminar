package seminar.seminar13.g1058;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Main {
    public static void main(String[] args) {
//        Citire date din fisier text in lista
        List<Produs> listaProduse = new ArrayList<>();
        try(BufferedReader in = new BufferedReader(new FileReader("vanzari.csv"))){
            String linie;
            while ( (linie=in.readLine())!=null ){
                Produs produs = new Produs();
                String[] t = linie.split(",");
                produs.setCodProdus(Integer.parseInt(t[0].trim()));
                produs.setDenumireProdus(t[1].trim());
                produs.setPretUnitar(Double.parseDouble(t[2].trim()));
                t = in.readLine().split(",");
                int n = t.length;
                for (int i=0;i<n;i+=3){
                    Client client = new Client(
                            Integer.parseInt(t[i].trim()),
                            t[i+1].trim(),
                            Double.parseDouble(t[i+2].trim())
                    );
                    produs.add(client);
                }
                listaProduse.add(produs);
            }
        }
        catch (Exception ex){
            System.err.println(ex);
        }
        System.out.println("Lista de produse citita din fisierul text:");
        listaProduse.forEach(System.out::println);
//        Salvare date in fisier Json
        try(PrintWriter out = new PrintWriter("vanzari_.json")){
            JSONObject r = new JSONObject();
            r.put("Lista produse",listaProduse);
            r.write(out,4,0);
        }
        catch (Exception ex){
            System.err.println(ex);
        }

        try(PrintWriter out = new PrintWriter("vanzari.json")){
            JSONObject r = new JSONObject();

            JSONArray jsList = new JSONArray();
            for (var produs:listaProduse){
                JSONObject jsProdus = new JSONObject();
                jsProdus.put("Cod produs",produs.getCodProdus());
                jsProdus.put("Denumire",produs.getDenumireProdus());
                jsProdus.put("Pret unitar",produs.getPretUnitar());
                jsProdus.put("Lista clienti",produs.getClienti());
                jsList.put(jsProdus);
            }
            r.put("Lista produse",jsList);
            r.write(out,4,0);
        }
        catch (Exception ex){
            System.err.println(ex);
        }
//        Restaurare date din fisierul Json
        try(BufferedReader in = new BufferedReader(new FileReader("vanzari.json"))){
            listaProduse.clear();
            JSONTokener t = new JSONTokener(in);
            JSONObject r = new JSONObject(t);
            JSONArray prodArray = r.getJSONArray("Lista produse");
            for (int i=0;i< prodArray.length();i++){
                Produs produs = new Produs();
                JSONObject jsProdus = prodArray.getJSONObject(i);
                produs.setCodProdus(jsProdus.getInt("Cod produs"));
                produs.setDenumireProdus(jsProdus.getString("Denumire"));
                produs.setPretUnitar(jsProdus.getDouble("Pret unitar"));
                JSONArray jsClienti = jsProdus.getJSONArray("Lista clienti");
                for (int j=0;j< jsClienti.length();j++){
                    Client client = new Client();
                    JSONObject jsClient = jsClienti.getJSONObject(j);
                    client.setCodClient(jsClient.getInt("codClient"));
                    client.setNumeClient(jsClient.getString("numeClient"));
                    client.setCantitate(jsClient.getDouble("cantitate"));
                    produs.add(client);
                }
                listaProduse.add(produs);
            }
        }
        catch (Exception ex){
            System.err.println(ex);
        }
        System.out.println("Lista de produse restaurata din fisierul Json:");
        listaProduse.forEach(System.out::println);

        try(BufferedReader in = new BufferedReader(new FileReader("vanzari.json"))) {
            JSONTokener t = new JSONTokener(in);
            JSONObject r = new JSONObject(t);
            listaProduse = StreamSupport.stream( r.getJSONArray("Lista produse").spliterator(),false )
                    .map( item->{
                        JSONObject jsProdus = (JSONObject) item;
                        Produs produs = new Produs();
                        produs.setCodProdus(jsProdus.getInt("Cod produs"));
                        produs.setDenumireProdus(jsProdus.getString("Denumire"));
                        produs.setPretUnitar(jsProdus.getDouble("Pret unitar"));
                        List<Client> clienti =
                                StreamSupport.stream( jsProdus.getJSONArray("Lista clienti").spliterator(),false )
                                .map(itemC->{
                                    JSONObject jsClient = (JSONObject) itemC;
                                    Client client = new Client();
                                    client.setCodClient(jsClient.getInt("codClient"));
                                    client.setNumeClient(jsClient.getString("numeClient"));
                                    client.setCantitate(jsClient.getDouble("cantitate"));
                                    return client;
                                }).collect(Collectors.toList());
                        produs.setClienti(clienti);
                        return produs;
                    } ).collect(Collectors.toList());
        }
        catch (Exception ex){
            System.err.println(ex);
        }
        System.out.println("Lista de produse restaurata prin stream din fisierul Json:");
        listaProduse.forEach(System.out::println);

//        Salvare in Xml
        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
            Element radacina = doc.createElement("Produse");
            doc.appendChild(radacina);
//            Adaugare elemente din lista Java
            for (Produs produs:listaProduse){
                Element nodProdus = doc.createElement("Produs");
                nodProdus.setAttribute("Cod_produs", String.valueOf(produs.getCodProdus()));
                nodProdus.setAttribute("Pret_unitar", String.valueOf(produs.getPretUnitar()));
                nodProdus.appendChild(doc.createTextNode(produs.getDenumireProdus()));
                for (Client client : produs.getClienti()) {
                    Element nodClient = doc.createElement("Client");
                    nodClient.setAttribute("Cod_client",String.valueOf(client.getCodClient()));
                    nodClient.setAttribute("Cantitate",String.valueOf(client.getCantitate()));
                    nodClient.appendChild(doc.createTextNode(client.getNumeClient()));
                    nodProdus.appendChild(nodClient);
                }
                radacina.appendChild(nodProdus);
            }
//            Salvare
            TransformerFactory tfact = TransformerFactory.newInstance();
            Transformer transformer = tfact.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult out = new StreamResult("vanzari.xml");
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            transformer.transform(source,out);
        }
        catch (Exception ex){
            System.err.println(ex);
        }

        //        Restaurare din XML
        try{
            listaProduse.clear();
            TransformerFactory tFact = TransformerFactory.newInstance();
            Transformer transf = tFact.newTransformer();
            StreamSource source = new StreamSource("vanzari.xml");
            DOMResult result = new DOMResult();
            transf.transform(source,result);
            Node radacina = result.getNode();
            NodeList nodeListProd = radacina.getFirstChild().getChildNodes();
            for (int i=0;i<nodeListProd.getLength();i++){
                Node nodeProd = nodeListProd.item(i);
                if (nodeProd.getNodeType()==Node.ELEMENT_NODE) {
                    Produs produs = new Produs();
                    produs.setCodProdus(Integer.parseInt(nodeProd.getAttributes().getNamedItem("Cod_produs").getNodeValue()));
                    produs.setPretUnitar(Double.parseDouble(nodeProd.getAttributes().getNamedItem("Pret_unitar").getNodeValue()));
                    produs.setDenumireProdus(nodeProd.getFirstChild().getNodeValue().trim());
                    NodeList nodeListClinet = nodeProd.getChildNodes();
                    for (int j=1;j< nodeListClinet.getLength();j++){
                        Node nodeClient = nodeListClinet.item(j);
                        if (nodeClient.getNodeName().trim().equalsIgnoreCase("Client")){
                            Client client = new Client();
                            client.setCodClient(Integer.parseInt(nodeClient.getAttributes().getNamedItem("Cod_client").getNodeValue()));
                            client.setCantitate(Double.parseDouble(nodeClient.getAttributes().getNamedItem("Cantitate").getNodeValue()));
                            client.setNumeClient(nodeClient.getFirstChild().getNodeValue().trim());
                            produs.add(client);
                        }
                    }
                    listaProduse.add(produs);
                }
            }
        }
        catch (Exception ex){
            System.err.println(ex);
        }
        System.out.println("Lista produse reastaurata din fisierul Xml:");
        listaProduse.forEach(System.out::println);
    }
}
