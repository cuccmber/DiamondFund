package com.yasuchenya.xml.handler;

public enum CrystalXmlTag {
    FUND("fund"),
    GEMSTONE("gemstone"),
    NAME("name"),
    ID("id"),
    TYPE("type"),
    YEAR("year"),
    SIZE("size"),
    ORIGIN("origin"),
    COLOUR("colour"),
    TRANSPARENCY("transparency"),
    LUSTER("luster"),
    CUT("cut");

    private String tag;

    CrystalXmlTag(String tag){
        this.tag = tag;
    }

    public String getTag(){
        return tag;
    }


}
