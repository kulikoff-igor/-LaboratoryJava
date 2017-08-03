package jobXML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by admin on 19.04.2017.
 */
public class DeleteXML {
    private String fileName = "index.xml";
    private Document document;

    public void deleteItem(String date) {
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            document = documentBuilder.parse(fileName);
            NodeList nodeList = document.getElementsByTagName("Date");
            int index;
            for (index = 0; index < nodeList.getLength(); index++) {
                Element day = (Element) nodeList.item(index);
                String pName = day.getTextContent();
                if (pName.equals(date)) {
                    delete(index);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void delete(int index) {
        Element element = (Element) document.getElementsByTagName("Day").item(index);

        Node parent = element.getParentNode();
        parent.removeChild(element);
        parent.normalize();
        AddXML write = new AddXML();
        write.writeDocument(document);
    }
}
