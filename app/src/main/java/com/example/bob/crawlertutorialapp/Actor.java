package com.example.bob.crawlertutorialapp;

public class Actor {
    private String nume;
    private String personaj;
    private String poster;
    private String link;

    public Actor(String nume, String personaj, String poster, String link) {
        this.nume = nume;
        this.personaj = personaj;
        this.poster = poster;
        this.link = link;
    }
    public Actor(String nume, String personaj, String poster) {
        this.nume = nume;
        this.personaj = personaj;
        this.poster = poster;
    }

    public String getNume() {
        return nume;
    }

    public String getPersonaj() {
        return personaj;
    }

    public String getPoster() {
        return poster;
    }

    public String getLink() {
        return link;
    }
}
