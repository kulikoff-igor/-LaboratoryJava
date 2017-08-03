package server;


import cobraApp.ServerCobraOperations;

import cobraApp._ServerCobraImplBase;
import cobraApp._ServerCobraStub;
import jobXML.AddXML;
import jobXML.DeleteXML;
import jobXML.ViewXML;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by admin on 19.04.2017.
 */
public class ServerServant extends _ServerCobraImplBase {
    public String viewFile() {
        ViewXML viewXML = new ViewXML();
        return viewXML.viewFiles();
    }

    public void addFile(String day) {
        AddXML job = new AddXML();
        job.addFile(day);
    }

    public void deleteData(String data) {
        DeleteXML deleteXML = new DeleteXML();
        deleteXML.deleteItem(data);
    }


}
