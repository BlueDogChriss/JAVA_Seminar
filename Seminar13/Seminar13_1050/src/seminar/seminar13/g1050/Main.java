package seminar.seminar13.g1050;

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
        List<Student> listaStudenti = new ArrayList<>();
        try(BufferedReader in = new BufferedReader(new FileReader("studenti.csv"))){
            String linie;
            while ( (linie=in.readLine())!=null ){
                Student student = new Student();
                String[] t = linie.split(",");
                student.setCnp(Long.parseLong(t[0].trim()));
                student.setNume(t[1].trim());
                t = in.readLine().split(",");
                int n = t.length;
                for (int i=0;i<n;i+=3){
                    Disciplina disciplina = new Disciplina();
                    disciplina.setNumeDisciplina(t[i]);
                    disciplina.setNumarCredite(Integer.parseInt(t[i+1].trim()));
                    disciplina.setNota(Integer.parseInt(t[i+2].trim()));
                    student.add(disciplina);
                }
                listaStudenti.add(student);
            }
        }
        catch (Exception ex){
            System.err.println(ex);
        }
        System.out.println("Lista citita din fisierul text:");
        listaStudenti.forEach(System.out::println);

//        Salvare in fisier Json
        try(PrintWriter out = new PrintWriter("studenti_.json")){
            JSONObject jo = new JSONObject();
            jo.put("Lista Studenti",listaStudenti);
            jo.write(out,4,0);
        }
        catch (Exception ex){
            System.err.println(ex);
        }

        try(PrintWriter out = new PrintWriter("studenti.json")){
            JSONObject jo = new JSONObject();
            JSONArray jsList = new JSONArray();
            for (var student:listaStudenti){
                JSONObject jsonStudent = new JSONObject();
                jsonStudent.put("CNP",student.getCnp());
                jsonStudent.put("Nume",student.getNume());
                jsonStudent.put("Catalog",student.getCatalog());
                jsList.put(jsonStudent);
            }
            jo.put("Lista Studenti",jsList);
            jo.write(out,4,0);
        }
        catch (Exception ex){
            System.err.println(ex);
        }

//        Restaurare date din fisierul Json
        try(BufferedReader in = new BufferedReader(new FileReader("studenti.json"))){
            listaStudenti.clear();
            JSONTokener jsonTokener = new JSONTokener(in);
            JSONObject rad = new JSONObject(jsonTokener);
            JSONArray studArray = rad.getJSONArray("Lista Studenti");
            for (int i=0;i<studArray.length();i++){
                Student student = new Student();
                JSONObject jsStudent = studArray.getJSONObject(i);
                student.setCnp(jsStudent.getLong("CNP"));
                student.setNume(jsStudent.getString("Nume"));
                JSONArray discipArray = jsStudent.getJSONArray("Catalog");
                for (int j=0;j< discipArray.length();j++){
                    Disciplina disciplina = new Disciplina();
                    JSONObject jsDisciplina = discipArray.getJSONObject(j);
                    disciplina.setNumeDisciplina(jsDisciplina.getString("numeDisciplina"));
                    disciplina.setNumarCredite(jsDisciplina.getInt("numarCredite"));
                    disciplina.setNota(jsDisciplina.getInt("nota"));
                    student.add(disciplina);
                }
                listaStudenti.add(student);
            }
            System.out.println("Lista restaurata dupa citirea din fisierul Json:");
            listaStudenti.forEach(System.out::println);
        }
        catch (Exception ex){
            System.err.println(ex);
        }
// Citire din Json cu stream
        try(BufferedReader in = new BufferedReader(new FileReader("studenti.json"))){
            JSONTokener jsonTokener = new JSONTokener(in);
            JSONObject rad = new JSONObject(jsonTokener);
            listaStudenti = StreamSupport.stream(
                    rad.getJSONArray("Lista Studenti").spliterator(),false
            ).map(item->{
                JSONObject objStudent = (JSONObject) item;
                Student student = new Student();
                student.setNume(objStudent.getString("Nume"));
                student.setCnp(objStudent.getLong("CNP"));
                List<Disciplina> discipline = StreamSupport.stream( objStudent.getJSONArray("Catalog").spliterator(),false )
                        .map(itemD->{
                            JSONObject jsDiscip = (JSONObject) itemD;
                            return new Disciplina(
                                    jsDiscip.getString("numeDisciplina"),
                                    jsDiscip.getInt("numarCredite"),
                                    jsDiscip.getInt("nota"));
                        }).collect(Collectors.toList());
                student.setCatalog(discipline);
                return student;
            }).collect(Collectors.toList());
            System.out.println("Restaurarea din Json prin stream:");
            listaStudenti.forEach(System.out::println);
        }
        catch (Exception ex){
            System.err.println(ex);
        }

//        Salvare in fisier Xml
        try{
            DocumentBuilderFactory docF = DocumentBuilderFactory.newInstance();
            DocumentBuilder docB = docF.newDocumentBuilder();
            Document doc = docB.newDocument();
//            Creare arbore Document
            Element rad = doc.createElement("Studenti");
            doc.appendChild(rad);
            for (Student student : listaStudenti) {
                Element nodStudent = doc.createElement("Student");
                nodStudent.setAttribute("CNP", String.valueOf(student.getCnp()));
                nodStudent.appendChild(doc.createTextNode(student.getNume()));
                for (Disciplina disciplina : student.getCatalog()) {
                    Element nodDisciplina = doc.createElement("Disciplina");
                    nodDisciplina.setAttribute("Nota",String.valueOf(disciplina.getNota()));
                    nodDisciplina.setAttribute("Numar_credite",String.valueOf(disciplina.getNumarCredite()));
                    nodDisciplina.appendChild(doc.createTextNode(disciplina.getNumeDisciplina()));
                    nodStudent.appendChild(nodDisciplina);
                }
                rad.appendChild(nodStudent);
            }
//            Salvare arbore in fisier Xml
            TransformerFactory tFact = TransformerFactory.newInstance();
            Transformer transformer = tFact.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult out = new StreamResult("studenti.xml");
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            transformer.transform(source,out);
        }
        catch (Exception ex){
            System.err.println(ex);
        }

//        Restaurare lista din fisier XML
        try{
            listaStudenti.clear();
            TransformerFactory tFact = TransformerFactory.newInstance();
            Transformer transf = tFact.newTransformer();
            StreamSource source = new StreamSource("studenti.xml");
            DOMResult result = new DOMResult();
            transf.transform(source,result);
            Node radacina = result.getNode();
            NodeList nodeListStud = radacina.getFirstChild().getChildNodes();
            for (int i=0;i<nodeListStud.getLength();i++){
                Node nodeStud = nodeListStud.item(i);
                if (nodeStud.getNodeType()==Node.ELEMENT_NODE) {
                    Student student = new Student();
                    student.setCnp(Long.parseLong(nodeStud.getAttributes().getNamedItem("CNP").getNodeValue()));
                    student.setNume(nodeStud.getFirstChild().getNodeValue().trim());
                    NodeList ndisc = nodeStud.getChildNodes();
                    for (int j=1;j< ndisc.getLength();j++){
                        Node node = ndisc.item(j);
                        if (node.getNodeName().trim().equalsIgnoreCase("Disciplina")){
                            Disciplina disciplina = new Disciplina();
                            disciplina.setNota(Integer.parseInt(node.getAttributes().getNamedItem("Nota").getNodeValue()));
                            disciplina.setNumarCredite(Integer.parseInt(node.getAttributes().getNamedItem("Numar_credite").getNodeValue()));
                            disciplina.setNumeDisciplina(node.getFirstChild().getNodeValue().trim());
                            student.add(disciplina);
                        }
                    }
                    listaStudenti.add(student);
                }
            }
            System.out.println("Lista studenti restaurata din fisierul Xml:");
            listaStudenti.forEach(System.out::println);
        }
        catch (Exception ex){
            System.err.println(ex);
        }
    }
}

