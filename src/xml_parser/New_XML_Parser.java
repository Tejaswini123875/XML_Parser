package xml_parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class New_XML_Parser {

    public static void main(String[] args) {
        String xmlFilePath = "laptops.xml"; 
        ArrayList<Laptop> laptops = parseLaptopsXML(xmlFilePath);

        // Print the parsed laptop data
        for (Laptop laptop : laptops) {
            System.out.println(laptop);
        }
    }

    public static ArrayList<Laptop> parseLaptopsXML(String filePath) {
        ArrayList<Laptop> laptops = new ArrayList<>();
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            NodeList laptopNodes = doc.getElementsByTagName("laptop");

            for (int i = 0; i < laptopNodes.getLength(); i++) {
                Node laptopNode = laptopNodes.item(i);
                if (laptopNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element laptopElement = (Element) laptopNode;
                    String name = laptopElement.getAttribute("name");
                    String price = laptopElement.getElementsByTagName("price").item(0).getAttributes().getNamedItem("value").getNodeValue();
                    String ram = laptopElement.getElementsByTagName("RAM").item(0).getAttributes().getNamedItem("value").getNodeValue();
                    String ssd = laptopElement.getElementsByTagName("SSD").item(0).getAttributes().getNamedItem("Value").getNodeValue();

                    Laptop laptop = new Laptop(name, price, ram, ssd);
                    laptops.add(laptop);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return laptops;
    }
}

class Laptop {
    private String name;
    private String price;
    private String ram;
    private String ssd;

    public Laptop(String name, String price, String ram, String ssd) {
        this.name = name;
        this.price = price;
        this.ram = ram;
        this.ssd = ssd;
    }

    @Override
    public String toString() {
        return "Laptop [Name: " + name + ", Price: " + price + ", RAM: " + ram + ", SSD: " + ssd + "]";
    }
}
