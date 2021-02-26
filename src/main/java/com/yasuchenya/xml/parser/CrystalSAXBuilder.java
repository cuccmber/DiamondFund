package com.yasuchenya.xml.parser;

import com.yasuchenya.xml.entity.Crystal;
import com.yasuchenya.xml.handler.CrystalErrorHandler;
import com.yasuchenya.xml.handler.CrystalHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class CrystalSAXBuilder {
    private static Logger logger = LogManager.getLogger();
    private Set<Crystal> crystals;
    private CrystalHandler handler = new CrystalHandler();
    private XMLReader reader;

    public CrystalSAXBuilder(){
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser;
        try {
            saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            logger.error("Error occurred while configuring SAX");
        }
        reader.setErrorHandler(new CrystalErrorHandler());
        reader.setContentHandler(handler);
    }

    public Set<Crystal> getCrystals(){
        return crystals;
    }

    public void buildSetCrystals(String filePath){
        try{
            reader.parse(filePath);
        } catch (IOException | SAXException e) {
            logger.error(filePath + "doesn't exist");
        }
        crystals = handler.getCrystals();
    }
}
