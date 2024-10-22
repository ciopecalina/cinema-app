package com.example.bob.crawlertutorialapp;

public class PostTV {
    private String nume;
    private String link;

    public PostTV(String nume, String link) {
        this.nume = nume;
        this.link = link;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
