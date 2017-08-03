package jobXML;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by ASUS on 20.04.2017.
 */
public class ViewXML {
    private String fileName = "index.xml";

    public String viewFiles() {
        JSONArray array = new JSONArray();
        JSONObject object = new JSONObject();
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
            while (reader.hasNext()) {
                XMLEvent xmlEvent = reader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();

                    if (startElement.getName().getLocalPart().equals("Day")) {


                    } else if (startElement.getName().getLocalPart().equals("Date")) {
                        xmlEvent = reader.nextEvent();
                        object.put("Date", xmlEvent.asCharacters().getData());
                    }
                    else if (startElement.getName().getLocalPart().equals("Temp")) {
                        xmlEvent = reader.nextEvent();
                        object.put("Temp",xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("Pressure")) {
                        xmlEvent = reader.nextEvent();
                        object.put("Pressure", xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("Wind")) {
                        xmlEvent = reader.nextEvent();
                        object.put("Wind", xmlEvent.asCharacters().getData());
                    }
                }

                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("Day")) {
                        array.put(object);
                        object = new JSONObject();
                    }
                }

            }

        } catch (FileNotFoundException | XMLStreamException exc) {
           System.out.println("Files is no found!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array.toString();
    }
}
