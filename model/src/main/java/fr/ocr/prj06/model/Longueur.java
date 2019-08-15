package fr.ocr.prj06.model;

public class Longueur {
    private String nom;
    private String cotation;
    private Integer nombreDeSpits;

    public Longueur() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCotation() {
        return cotation;
    }

    public void setCotation(String cotation) {
        this.cotation = cotation;
    }

    public Integer getNombreDeSpits() {
        return nombreDeSpits;
    }

    public void setNombreDeSpits(Integer nombreDeSpits) {
        this.nombreDeSpits = nombreDeSpits;
    }
}
