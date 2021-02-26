package com.yasuchenya.xml.handler;

import com.yasuchenya.xml.entity.Crystal;
import com.yasuchenya.xml.entity.Gemstone;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.Year;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class CrystalHandler extends DefaultHandler {
    private Set<Crystal> crystals;
    private Crystal current;
    private CrystalXmlTag currentXmlTag;
    private EnumSet<CrystalXmlTag> withText;
    private static final String ELEMENT_CRYSTAL = "gemstone";
    private final static String DEFAULT_STRING = "default";
    public final static double DEFAULT_DOUBLE = 0;
    public final static int DEFAULT_INT = 0;

    public CrystalHandler(){
        crystals = new HashSet<>();
        withText = EnumSet.range(CrystalXmlTag.NAME, CrystalXmlTag.CUT);
    }

    public Set<Crystal> getCrystals(){
        return crystals;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs){
        if(ELEMENT_CRYSTAL.equals(qName)){
            current = new Gemstone(DEFAULT_STRING, DEFAULT_STRING, Year.of(DEFAULT_INT), DEFAULT_DOUBLE,
                    DEFAULT_STRING, DEFAULT_STRING,DEFAULT_STRING, DEFAULT_STRING, DEFAULT_STRING);
            if(attrs.getLength() == 2) {
                current.setName(attrs.getValue(0));
                current.setId(attrs.getValue(1));
            } else {
                current.setId(attrs.getValue(0));
            }
        } else {
            CrystalXmlTag temp = CrystalXmlTag.valueOf(qName.toUpperCase());
            if(withText.contains(temp)){
                currentXmlTag = temp;
            }
        }
    }

    public void endElement(String uri, String localName, String qName){
        if(ELEMENT_CRYSTAL.equals(qName)){
            crystals.add(current);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).strip();
        if(currentXmlTag != null){
            switch (currentXmlTag){
                case TYPE -> current.setType(data);
                case YEAR -> current.setYear(Year.parse(data));
                case SIZE -> current.setSize(Double.parseDouble(data));
                case ORIGIN -> current.setOrigin(data);
                case COLOUR -> current.setColor(data);
                case TRANSPARENCY -> current.setTransparency(data);
                case LUSTER -> current.setLuster(data);
                case CUT -> current.setCut(data);
                default -> throw new EnumConstantNotPresentException(
                        currentXmlTag.getDeclaringClass(), currentXmlTag.name());
            }
        }
        currentXmlTag = null;
    }

}
