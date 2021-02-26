package com.yasuchenya.xml.parser;

import com.yasuchenya.xml.entity.Crystal;
import com.yasuchenya.xml.entity.Gemstone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;


public class CrystalDOMBuilder {
    private static Logger logger = LogManager.getLogger();
    private Set<Crystal> crystals;
    private DocumentBuilder documentBuilder;
    private final static String ROOT_ELEMENT = "gemstone";
    private final static String NAME_ATTRIBUTE = "name";
    private final static String ID_ATTRIBUTE = "id";
    private final static String TYPE_ELEMENT = "type";
    private final static String YEAR_ELEMENT = "year";
    private final static String SIZE_ELEMENT = "size";
    private final static String ORIGIN_ELEMENT = "origin";
    private final static String COLOR_ELEMENT = "colour";
    private final static String TRANSPARENCY_ELEMENT = "transparency";
    private final static String LUSTER_ELEMENT = "luster";
    private final static String CUT_ELEMENT = "cut";


    public CrystalDOMBuilder() {
        crystals = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error("Error occurred while DOM configuration");
        }
    }

    public Set<Crystal> getCrystals(){
        return crystals;
    }

    public void buildSetCrystals(String filename){
        Document document;
        try {
            document = documentBuilder.parse(filename);
            Element root = document.getDocumentElement();
            NodeList crystalList = root.getElementsByTagName(ROOT_ELEMENT);
            for(int i = 0; i < crystalList.getLength(); i++){
                Element crystalElement = (Element) crystalList.item(i);
                Crystal crystal = buildGem(crystalElement);
                crystals.add(crystal);
            }
        } catch (SAXException | IOException e) {
            logger.error("Error occurred while reading xml");
        }
    }

    private Crystal buildGem(Element crystalElement){
        String name = crystalElement.getAttribute(NAME_ATTRIBUTE);
        String id =crystalElement.getAttribute(ID_ATTRIBUTE);
        String type = getElementTextContent(crystalElement, TYPE_ELEMENT);
        Year year = Year.parse(getElementTextContent(crystalElement, YEAR_ELEMENT));
        double size = Double.parseDouble(getElementTextContent(crystalElement, SIZE_ELEMENT));
        String origin = getElementTextContent(crystalElement, ORIGIN_ELEMENT);
        String color = getElementTextContent(crystalElement, COLOR_ELEMENT);
        String transparency = getElementTextContent(crystalElement, TRANSPARENCY_ELEMENT);
        String luster = getElementTextContent(crystalElement, LUSTER_ELEMENT);
        String cut = getElementTextContent(crystalElement, CUT_ELEMENT);
        Crystal crystal = new Gemstone(id, type, year, size, origin, color, transparency, luster, cut);
        crystal.setName(name);
        return crystal;
    }

    private static String getElementTextContent(Element element, String elementName){
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        String textContent = node.getTextContent();
        return textContent;
    }
}
