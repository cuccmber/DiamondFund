package com.yasuchenya.xml.parser;

import com.yasuchenya.xml.entity.Crystal;
import com.yasuchenya.xml.entity.Gemstone;
import com.yasuchenya.xml.handler.CrystalXmlTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

public class CrystalStAXBuilder {
    private static Logger logger = LogManager.getLogger();
    private Set<Crystal> crystals;
    private XMLInputFactory inputFactory;
    private final static String DEFAULT_STRING = "default";
    public final static double DEFAULT_DOUBLE = 0;
    public final static int DEFAULT_INT = 0;

    public CrystalStAXBuilder(){
        inputFactory = XMLInputFactory.newInstance();
        crystals = new HashSet<>();
    }

    public Set<Crystal> getCrystals(){
        return crystals;
    }

    public void buildSetCrystals(String filename){
        XMLStreamReader reader;
        String name;
        try(FileInputStream inputStream = new FileInputStream(new File(filename))) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(CrystalXmlTag.GEMSTONE.getTag())) {
                        Crystal crystal = buildCrystal(reader);
                        crystals.add(crystal);
                    }
                }
            }
        } catch (XMLStreamException | IOException e) {
            logger.error("Error occurred while configuring StAX");
        }
    }

    private Crystal buildCrystal(XMLStreamReader reader) throws XMLStreamException {
        Crystal crystal = new Gemstone(DEFAULT_STRING, DEFAULT_STRING, Year.of(DEFAULT_INT), DEFAULT_DOUBLE,
                DEFAULT_STRING, DEFAULT_STRING,DEFAULT_STRING, DEFAULT_STRING, DEFAULT_STRING);
        crystal.setId(reader.getAttributeValue(null, CrystalXmlTag.ID.getTag()));
        if(reader.getAttributeValue(null, CrystalXmlTag.NAME.getTag()) != null){
            crystal.setName(reader.getAttributeValue(null, CrystalXmlTag.NAME.getTag()));
        }
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (CrystalXmlTag.valueOf(name.toUpperCase())) {
                        case TYPE -> crystal.setType(getXmlText(reader));
                        case YEAR -> crystal.setYear(Year.parse(getXmlText(reader)));
                        case SIZE -> crystal.setSize(Double.parseDouble(getXmlText(reader)));
                        case ORIGIN -> crystal.setOrigin(getXmlText(reader));
                        case COLOUR -> crystal.setColor(getXmlText(reader));
                        case TRANSPARENCY -> crystal.setTransparency(getXmlText(reader));
                        case LUSTER -> crystal.setLuster(getXmlText(reader));
                        case CUT -> crystal.setCut(getXmlText(reader));
                    }
                        break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                if (CrystalXmlTag.valueOf(name.toUpperCase()) == CrystalXmlTag.GEMSTONE) {
                    return crystal;
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag <gemstone>");
    }
    private String getXmlText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
