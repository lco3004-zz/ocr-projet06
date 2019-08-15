package fr.ocr.prj06.model;

import java.util.Date;

public class Topo {
    private String nom;
    private byte estPublie;
    private byte estDisponible;
    private String resume;
    private String lieu;
    private java.util.Date dateDeParution;

    public Topo() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public byte getEstPublie() {
        return estPublie;
    }

    public void setEstPublie(byte estPublie) {
        this.estPublie = estPublie;
    }

    public byte getEstDisponible() {
        return estDisponible;
    }

    public void setEstDisponible(byte estDisponible) {
        this.estDisponible = estDisponible;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Date getDateDeParution() {
        return dateDeParution;
    }

    public void setDateDeParution(Date dateDeParution) {
        this.dateDeParution = dateDeParution;
    }
}
