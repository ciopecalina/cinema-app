package com.example.bob.crawlertutorialapp;

public class ParseItemPremii {
    private String titlu;
    private String categorie;
    private String nume;

    public ParseItemPremii(String titlu, String categorie, String nume) {
        this.titlu = titlu;
        this.categorie = categorie;
        this.nume = nume;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
}
