package com.suman.kennelservice.model;

public class Dogbreeds {

    private String name;
    private String image;
    private String description;
    private String desc;

    public Dogbreeds(String name, String image, String description, String desc) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
