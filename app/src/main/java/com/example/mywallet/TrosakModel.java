package com.example.mywallet;

public class TrosakModel {
    private int id;
    private String kategorija;
    private int vrijednost;
    private String biljeska;
    private int id_korisnika;

    public TrosakModel(int id, String kategorija, int vrijednost, String biljeska, int id_korisnika) {
        this.id = id;
        this.kategorija = kategorija;
        this.vrijednost = vrijednost;
        this.biljeska = biljeska;
        this.id_korisnika = id_korisnika;
    }

    public TrosakModel() {
    }

    @Override
    public String toString() {
        return "Trosak{" +
                "id=" + id +
                ", kategorija='" + kategorija + '\'' +
                ", vrijednost=" + vrijednost +
                ", biljeska='" + biljeska + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public int getVrijednost() {
        return vrijednost;
    }

    public void setVrijednost(int vrijednost) {
        this.vrijednost = vrijednost;
    }

    public String getBiljeska() {
        return biljeska;
    }

    public void setBiljeska(String biljeska) {
        this.biljeska = biljeska;
    }

    public int getId_korisnika() {
        return id_korisnika;
    }

    public void setId_korisnika(int id_korisnika) {
        this.id_korisnika = id_korisnika;
    }
}
