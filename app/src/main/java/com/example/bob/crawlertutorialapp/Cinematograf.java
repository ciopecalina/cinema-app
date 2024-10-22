package com.example.bob.crawlertutorialapp;

public class Cinematograf {
    private int image;
    private String name, adress;
    private String link;
    private String lat;
    private String longi;

    public Cinematograf(String name, String adress, String link, String lat, String longi) {
        this.name = name;
        this.adress = adress;
        this.link = link;
        this.lat = lat;
        this.longi = longi;
    }

    public Cinematograf(int image, String name, String adress) {
        this.image = image;
        this.name = name;
        this.adress = adress;
    }

    public Cinematograf(int image, String name, String adress, String link) {
        this.image = image;
        this.name = name;
        this.adress = adress;
        this.link=link;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
