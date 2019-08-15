package fr.ocr.prj06.model;

public class Commentaire {

    private Integer idcommentaire;
    private String texte;
    private Boolean estVisible;
    private Integer idSpot;

    public Commentaire(Integer idSpot) {
        this.idSpot = idSpot;
    }

    public Commentaire() {
    }

    public Integer getIdcommentaire() {
        return idcommentaire;
    }

    public void setIdcommentaire(Integer idcommentaire) {
        this.idcommentaire = idcommentaire;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public Boolean getEstVisible() {
        return estVisible;
    }

    public void setEstVisible(Boolean estVisible) {
        this.estVisible = estVisible;
    }

    public Integer getIdSpot() {
        return idSpot;
    }

    public void setIdSpot(Integer idSpot) {
        this.idSpot = idSpot;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(1024);
        s.append("idCommentaire= ").append(idcommentaire)
                .append(" idSpot= ").append(idSpot)
                .append("  estVisible= ").append(estVisible)
                .append(" texte=").append(texte);
        return s.toString();
    }
}
