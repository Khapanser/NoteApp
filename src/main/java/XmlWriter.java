package main.java;

import java.io.File;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

//import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlWriter {
    /**
     *
     * @param listModel
     * @param file
     */
    public void writer(DefaultListModel<QCard> listModel, File file) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("company");
            doc.appendChild(rootElement);
            // staff elements

            for (int i = 0; i < listModel.getSize(); i++) {
                //берём i карточку
                QCard ca = listModel.getElementAt(i);

                Element staff = doc.createElement("staff");
                rootElement.appendChild(staff);
                // set attribute to staff element

            /*Attr attr = doc.createAttribute("id");
            attr.setValue("1");
            staff.setAttributeNode(attr);*/

                // shorten way
                // staff.setAttribute("id", "1");
                // title elements
                Element title = doc.createElement("title");
                title.appendChild(doc.createTextNode(ca.getTitle()));
                staff.appendChild(title);
                // description elements
                Element description = doc.createElement("description");
                description.appendChild(doc.createTextNode(ca.getDescription()));
                staff.appendChild(description);
            }


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            //тут надо запускать с уже указанной директорией файла.

            StreamResult result = new StreamResult(file);
            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);
            System.out.println("File saved!");
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

}
