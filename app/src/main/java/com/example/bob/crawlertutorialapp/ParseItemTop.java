package com.example.bob.crawlertutorialapp;

public class ParseItemTop {
    private String poster;
    private String titlu;
    private String nota;
    private String url;
    private String nr;

    public ParseItemTop(String poster, String titlu, String nota, String url,String nr) {
        this.poster = poster;
        this.titlu = titlu;
        this.nota = nota;
        this.url = url;
        this.nr= nr;
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

    public String getUrl() {
        return url;
    }
}
