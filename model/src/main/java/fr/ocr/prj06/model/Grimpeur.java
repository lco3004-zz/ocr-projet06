package fr.ocr.prj06.model;

import java.util.Set;

public class Grimpeur {
    private String monNom;
    private String monEmail;
    private String monMdp;
    private String monProfil;
    private Set<Topo> mesTopos;
    private Set<Spot> mesSpots;
    private Set<Commentaire> mesCommentaires;

    public Grimpeur() {

    }

    public Set<Commentaire> getMesCommentaires() {
        return mesCommentaires;
    }

    public void setMesCommentaires(Set<Commentaire> mesCommentaires) {
        this.mesCommentaires = mesCommentaires;
    }

    public String getMonNom() {
        return monNom;
    }

    public void setMonNom(String monNom) {
        this.monNom = monNom;
    }

    public String getMonEmail() {
        return monEmail;
    }

    public void setMonEmail(String monEmail) {
        this.monEmail = monEmail;
    }

    public String getMonMdp() {
        return monMdp;
    }

    public void setMonMdp(String monMdp) {
        this.monMdp = monMdp;
    }

    public String getMonProfil() {
        return monProfil;
    }

    public void setMonProfil(String monProfil) {
        this.monProfil = monProfil;
    }

    public Set<Topo> getMesTopos() {
        return mesTopos;
    }

    public void setMesTopos(Set<Topo> mesTopos) {
        this.mesTopos = mesTopos;
    }

    public Set<Spot> getMesSpots() {
        return mesSpots;
    }

    public void setMesSpots(Set<Spot> mesSpots) {
        this.mesSpots = mesSpots;
    }
}
