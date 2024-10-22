package com.example.bob.crawlertutorialapp;

public class Watchlist {

    private String id;
    private String email_user;
    private String titlu_film;
    private String ora_film;
    private String data_film;
    private String postTv_film;

    public Watchlist(String email_user, String titlu_film, String ora_film, String data_film) {
        this.email_user = email_user;
        this.titlu_film = titlu_film;
        this.ora_film = ora_film;
        this.data_film = data_film;
    }

    public Watchlist() {
    }

    public Watchlist(String id, String email_user, String titlu_film, String ora_film, String data_film) {
        this.id = id;
        this.email_user = email_user;
        this.titlu_film = titlu_film;
        this.ora_film = ora_film;
        this.data_film = data_film;
    }

    public String getPostTv_film() {
        return postTv_film;
    }

    public void setPostTv_film(String postTv_film) {
        this.postTv_film = postTv_film;
    }

    public String getTitlu_film() {
        return titlu_film;
    }

    public void setTitlu_film(String titlu_film) {
        this.titlu_film = titlu_film;
    }

    public String getOra_film() {
        return ora_film;
    }

    public void setOra_film(String ora_film) {
        this.ora_film = ora_film;
    }

    public String getData_film() {
        return data_film;
    }

    public void setData_film(String data_film) {
        this.data_film = data_film;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }


}
