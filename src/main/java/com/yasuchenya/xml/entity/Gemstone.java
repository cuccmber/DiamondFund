package com.yasuchenya.xml.entity;

import java.time.Year;

public class Gemstone extends Crystal{

    private String cut;


    public Gemstone(String id, String type, Year dateOfMining, double size, String origin, String color,
                    String transparency, String luster, String cut) {
        super(id, type, dateOfMining, size, origin, color, transparency, luster);
        this.cut = cut;
    }

    @Override
    public String getCut(){
        return cut;
    }

    @Override
    public void setCut(String cut){
        this.cut = cut;
    }

    @Override
    public int hashCode(){
        final int PRIME = 31;
        int result = 1;
        result = super.hashCode();
        result = PRIME * result + cut.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object object){
        if(this == object){
            return true;
        }
        Gemstone temp = (Gemstone) object;
        return object instanceof Gemstone && cut.equals(temp.cut);
    }

    @Override
    public String toString(){
        return super.toString() + ", cut=" + cut;
    }
}
