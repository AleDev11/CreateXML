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
    public void WriterXML(String NameFile, String Addid, String Addfirstname, int AddNota, int Addlastname) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc =docBuilder.newDocument();
            Element rootElemant = doc.createElement("Esccuela");
            doc.appendChild(rootElemant);

            Element Estudiantes = doc.createElement("Estudiantes");
            rootElemant.appendChild(Estudiantes);

            Element Estudiante = doc.createElement("Estudiante");
            Estudiantes.appendChild(Estudiante);

            Attr attr = doc.createAttribute("id");
            attr.setValue(String.valueOf(Addid));
            Estudiante.setAttributeNode(attr);

            Element firstname = doc.createElement("firstname");
            firstname.appendChild(doc.createTextNode(Addfirstname));
            Estudiante.appendChild(firstname);

            Element lastname = doc.createElement("lastname");
            lastname.appendChild(doc.createTextNode(String.valueOf(Addlastname)));
            Estudiante.appendChild(lastname);

            Element nota = doc.createElement("nota");
            nota.appendChild(doc.createTextNode(String.valueOf(AddNota)));
            Estudiante.appendChild(nota);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new FileOutputStream(NameFile));
            transformer.transform(source, result);

            System.out.println("Alumno guardado con exito!");

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
