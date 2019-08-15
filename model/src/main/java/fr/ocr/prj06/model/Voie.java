package fr.ocr.prj06.model;

import java.util.Set;

public class Voie {
    private String nom;
    private Set<Longueur> longueurs;

    public Voie() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Longueur> getLongueurs() {
        return longueurs;
    }

    public void setLongueurs(Set<Longueur> longueurs) {
        this.longueurs = longueurs;
    }
}
