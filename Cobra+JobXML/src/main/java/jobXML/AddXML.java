package jobXML;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by admin on 19.04.2017.
 */
public class AddXML {
    private String fileName = "index.xml";
    private JSONObject dataJson;

    public void addFile(String day) {

        try {
            dataJson = new JSONObject(day);

            if ((new File(fileName)).exists()) {
                preparingAdd();
            } else {
                    try {
                        XMLOutputFactory output = XMLOutputFactory.newInstance();
                        XMLStreamWriter writer = output.createXMLStreamWriter(new FileWriter(fileName));
                        writer.writeStartDocument("1.0");
                        writer.writeStartElement("Weather");

                        writer.writeEndElement();
                        writer.writeEndDocument();
                        writer.flush();
                    } catch (XMLStreamException | IOException ex) {
                        ex.printStackTrace();
                    }
                preparingAdd();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void preparingAdd() throws JSONException {
        try {

            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(fileName);
            addNewItem(document);

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private void addNewItem(Document document) throws TransformerFactoryConfigurationError, DOMException, JSONException {
        Node root = document.getDocumentElement();

        Element day = document.createElement("Day");
        Element date = document.createElement("Date");
        date.setTextContent(dataJson.getString("Date"));
        Element temp = document.createElement("Temp");
        temp.setTextContent(dataJson.getString("Temp"));

        Element pressure = document.createElement("Pressure");
        pressure.setTextContent(dataJson.getString("Pressure"));

        Element wind = document.createElement("Wind");
        wind.setTextContent(dataJson.getString("Wind"));

        day.appendChild(date);
        day.appendChild(temp);
        day.appendChild(pressure);
        day.appendChild(wind);

        root.appendChild(day);

        writeDocument(document);
    }

    public void writeDocument(Document document) throws TransformerFactoryConfigurationError {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream(fileName);
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
