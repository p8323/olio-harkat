package com.example.vko9_t2_2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class XMLFeed {

    String url;
    public static ArrayList<Theatre> theatres = new ArrayList();
    String ID;
    String name;


    public void read() {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            url = "https://www.finnkino.fi/xml/TheatreAreas/";
            Document doc = builder.parse(url);
            doc.getDocumentElement().normalize();

            NodeList list = doc.getDocumentElement().getElementsByTagName("TheatreArea");

            for (int i=0; i<list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println(element.getElementsByTagName("ID").item(0).getTextContent());
                    System.out.println(element.getElementsByTagName("Name").item(0).getTextContent());
                    ID = element.getElementsByTagName("ID").item(0).getTextContent();
                    name = element.getElementsByTagName("Name").item(0).getTextContent();
                    theatres.add(new Theatre(ID, name));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Toimii");
        }
    }

}
