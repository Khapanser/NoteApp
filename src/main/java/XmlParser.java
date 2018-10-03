package main.java;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class XmlParser {
    /**
     *
     * @param fXmlFile
     * @return DefaultListModel<QCard>
     */
    DefaultListModel<QCard> listModel;
    public DefaultListModel<QCard> parser(File fXmlFile) {
        listModel = new DefaultListModel<QCard>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("staff");
            System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {

                    Node nNode = nList.item(temp);

                    System.out.println("\nCurrent Element :" + nNode.getNodeName());

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element eElement = (Element) nNode;
                        //ДОБАВЛЯЕМ СОЗДАНИЕ КАРТОЧКИ
                        QCard card = new QCard(eElement.getElementsByTagName("title").item(0).getTextContent(),
                                eElement.getElementsByTagName("description").item(0).getTextContent());
                        //добавляем лист
                        listModel.addElement(card);

                        System.out.println("title : " + eElement.getElementsByTagName("title").item(0).getTextContent());
                        System.out.println("description : " + eElement.getElementsByTagName("description").item(0).getTextContent());


                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return listModel;
        }

}
