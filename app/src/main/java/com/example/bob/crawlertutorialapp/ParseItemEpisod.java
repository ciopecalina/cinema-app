package com.example.bob.crawlertutorialapp;

public class ParseItemEpisod {
    private String nr;
    private String poster;
    private String titlu;
    private String nota;
    private String descriere;

    public ParseItemEpisod(String nr, String poster, String titlu, String nota, String descriere) {
        this.nr = nr;
        this.poster = poster;
        this.titlu = titlu;
        this.nota = nota;
        this.descriere = descriere;
    }

    public String getNr() {
        return nr;
    }

    public String getPoster() {
        return poster;
    }

    public String getTitlu() {
        return titlu;
    }

    public String getNota() {
        return nota;
    }

    public String getDescriere() {
        return descriere;
    }
}
