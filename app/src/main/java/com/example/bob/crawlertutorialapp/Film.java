package com.example.bob.crawlertutorialapp;

public class Film {

    private String ora;
    private String titlu;
    private String descriere;
    private String link;
    private String poster;

    private String postTV;
    private String data;

    public Film(String ora, String titlu, String descriere, String link, String poster) {
        this.ora = ora;
        this.titlu = titlu;
        this.descriere = descriere;
        this.link = link;
        this.poster = poster;
    }

 public Film(String ora, String titlu, String descriere, String link, String poster, String data, String postTV ) {
        this.ora = ora;
        this.titlu = titlu;
        this.descriere = descriere;
        this.link = link;
        this.poster = poster;
        this.postTV = postTV;
        this.data = data;
    }

    public Film(String titlu,String ora, String data,String postTv ) {
        this.ora = ora;
        this.titlu = titlu;
        this.data = data;
        this.postTV = postTV;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPostTV() {
        return postTV;
    }

    public void setPostTV(String postTV) {
        this.postTV = postTV;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
