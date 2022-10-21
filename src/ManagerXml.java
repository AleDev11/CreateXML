import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ManagerXml {

    private int getTotalId() {
        int totalIId = 0;

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(Main.NameFile);
            totalIId = doc.getElementsByTagName("Estudiante").getLength();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalIId;
    }

    public void createXml() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Escuela");
            doc.appendChild(rootElement);
            Element estudiantes = doc.createElement("Estudiantes");
            rootElement.appendChild(estudiantes);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileOutputStream(Main.NameFile));
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(String name, String lastName, int note) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(Main.NameFile);

            Element rootElement = doc.getDocumentElement();

            Element estudiantes = (Element) rootElement.getElementsByTagName("Estudiantes").item(0);

            Element estudiante = doc.createElement("Estudiante");
            estudiantes.appendChild(estudiante);

            Attr attr = doc.createAttribute("id");
            attr.setValue(String.valueOf(getTotalId() + 1));
            estudiante.setAttributeNode(attr);

            Element nombre = doc.createElement("Nombre");
            nombre.appendChild(doc.createTextNode(name));
            estudiante.appendChild(nombre);

            Element apellido = doc.createElement("Apellido");
            apellido.appendChild(doc.createTextNode(lastName));
            estudiante.appendChild(apellido);

            Element nota = doc.createElement("Nota");
            nota.appendChild(doc.createTextNode(String.valueOf(note)));
            estudiante.appendChild(nota);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileOutputStream(Main.NameFile));

            transformer.transform(source, result);

            System.out.println("[SYSTEM] File saved!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAllStudents() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(Main.NameFile);

            Element rootElement = doc.getDocumentElement();

            Element estudiantes = (Element) rootElement.getElementsByTagName("Estudiantes").item(0);

            for (int i = 0; i < estudiantes.getElementsByTagName("Estudiante").getLength(); i++) {
                Element estudiante = (Element) estudiantes.getElementsByTagName("Estudiante").item(i);

                System.out.println("==================================");
                System.out.println("ID: " + estudiante.getAttribute("id"));
                System.out.println("Nombre: " + estudiante.getElementsByTagName("Nombre").item(0).getTextContent());
                System.out.println("Apellido: " + estudiante.getElementsByTagName("Apellido").item(0).getTextContent());
                System.out.println("Nota: " + estudiante.getElementsByTagName("Nota").item(0).getTextContent());
                System.out.println("==================================");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchStudentWhereName(String name) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(Main.NameFile);

            Element rootElement = doc.getDocumentElement();

            Element estudiantes = (Element) rootElement.getElementsByTagName("Estudiantes").item(0);

            for (int i = 0; i < estudiantes.getElementsByTagName("Estudiante").getLength(); i++) {
                Element estudiante = (Element) estudiantes.getElementsByTagName("Estudiante").item(i);

                if (estudiante.getElementsByTagName("Nombre").item(0).getTextContent().equals(name)) {
                    System.out.println("==================================");
                    System.out.println("ID: " + estudiante.getAttribute("id"));
                    System.out.println("Nombre: " + estudiante.getElementsByTagName("Nombre").item(0).getTextContent());
                    System.out.println("Apellido: " + estudiante.getElementsByTagName("Apellido").item(0).getTextContent());
                    System.out.println("Nota: " + estudiante.getElementsByTagName("Nota").item(0).getTextContent());
                    System.out.println("==================================");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
