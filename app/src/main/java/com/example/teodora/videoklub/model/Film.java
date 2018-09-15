package com.example.teodora.videoklub.model;

/**
 * Created by Teodora on 6/6/18.
 */

public class Film {

    private String nazivFilma;
    private String kategorija;
    private String zanr;
    private int godina;
    private int idFilma;

    public Film(String nazivFilma, String kategorija, String zanr, int godina, int idFilma) {
        this.nazivFilma = nazivFilma;
        this.kategorija = kategorija;
        this.zanr = zanr;
        this.godina = godina;
        this.idFilma = idFilma;
    }

    public String getNazivFilma() {
        return nazivFilma;
    }

    public void setNazivFilma(String nazivFilma) {
        this.nazivFilma = nazivFilma;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    public int getIdFilma() {
        return idFilma;
    }

    public void setIdFilma(int idFilma) {
        this.idFilma = idFilma;
    }
}
