package com.yasuchenya.xml.entity;

import java.time.Year;

public abstract class Crystal {
    private String name = "unnamed";
    private String id;
    private String type;
    private Year year;
    private double size;
    private String origin;
    private String color;
    private String transparency;
    private String luster;

    public Crystal(String id,
            String type,
            Year year,
            double size,
            String origin,
            String color,
            String transparency,
            String luster){
        this.name = name;
        this.id = id;
        this.type = type;
        this.year = year;
        this.size = size;
        this.origin = origin;
        this.color = color;
        this.transparency = transparency;
        this.luster = luster;
    }

    public String getName(){
       return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public Year getYear(){
        return year;
    }

    public void setYear(Year year){
        this.year = year;
    }

    public double getSize(){
        return size;
    }

    public void setSize(double size){
        this.size = size;
    }

    public String getOrigin(){
        return origin;
    }

    public void setOrigin(String origin){
        this.origin = origin;
    }

    public String getColor(){
        return color;
    }

    public void setColor(String color){
        this.color = color;
    }

    public String getTransparency(){
        return transparency;
    }

    public void setTransparency(String transparency){
        this.transparency = transparency;
    }

    public String getLuster(){
        return luster;
    }

    public void setLuster(String luster){
        this.luster = luster;
    }

    public abstract String getCut();

    public abstract void setCut(String cut);

    @Override
    public int hashCode(){
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + name.hashCode();
        result = PRIME * result + id.hashCode();
        result = PRIME * result + type.hashCode();
        result = PRIME * result + year.hashCode();
        result = PRIME * result + (int) size;
        result = PRIME * result + origin.hashCode();
        result = PRIME * result + color.hashCode();
        result = PRIME * result + transparency.hashCode();
        result = PRIME * result + luster.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object object){
        if (this == object) {
            return true;
        }
        Crystal temp = (Crystal) object;
        return object instanceof Crystal && name.equals(temp.name) && id.equals(temp.id) && type.equals(temp.type)
                && year.equals(temp.year) && size == temp.size && origin.equals(temp.origin)
                && transparency.equals(temp.transparency) && luster.equals(temp.luster);
    }

    @Override
    public String toString(){
        return "Crystal:" +
                "id=" + id +
                ", name=" + name +
                ", type=" + type +
                ", dateOfMining=" + year +
                ", size=" + size +
                ", origin=" + origin +
                ", color=" + color +
                ", transparency=" + transparency +
                ", luster=" + luster;
    }
}
