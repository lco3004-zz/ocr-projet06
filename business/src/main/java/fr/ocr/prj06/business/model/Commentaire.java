package fr.ocr.prj06.business.model;

public class Commentaire {
    private String texte;
    private byte estVisible;

    public Commentaire() {

    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public byte getEstVisible() {
        return estVisible;
    }

    public void setEstVisible(byte estVisible) {
        this.estVisible = estVisible;
    }
}
