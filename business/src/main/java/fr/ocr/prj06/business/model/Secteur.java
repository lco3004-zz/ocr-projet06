package fr.ocr.prj06.business.model;

import fr.ocr.prj06.entity.stub.dbSpotEntity;

import java.util.Set;

public class Secteur {
    private String nom;
    private dbSpotEntity spotBySpotIdspot;
    private Set<Voie> voies;

    public Secteur() {

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public dbSpotEntity getSpotBySpotIdspot() {
        return spotBySpotIdspot;
    }

    public void setSpotBySpotIdspot(dbSpotEntity spotBySpotIdspot) {
        this.spotBySpotIdspot = spotBySpotIdspot;
    }

    public Set<Voie> getVoies() {
        return voies;
    }

    public void setVoies(Set<Voie> voies) {
        this.voies = voies;
    }
}
