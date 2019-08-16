package fr.ocr.prj06.model;

import fr.ocr.prj06.entity.stub.dbCommentaireEntity;
import fr.ocr.prj06.entity.stub.dbSpotEntity;
import org.apache.logging.log4j.Level;

import javax.persistence.EntityManager;
import java.util.Objects;

import static fr.ocr.prj06.utility.constante.Messages.ErreurMessages.MODELINTERNE_COPY_VERS_ENTITY_TXTINACT;
import static fr.ocr.prj06.utility.logs.LogsProjet.getLogsInstance;

public class Commentaire extends ModeleInterne {

    private Integer idcommentaire;
    private String texte;
    private Boolean estVisible;
    private Integer idSpot;

    public Commentaire(Integer idSpot) {
        this.idSpot = idSpot;
    }

    public Commentaire() {
        setLogs();
    }

    @Override
    protected void setLogs() {
        super.logs = getLogsInstance();
    }

    @Override
    public <E> void copyVersModele(E entity) {
        dbCommentaireEntity db = (dbCommentaireEntity) entity;
        this.setEstVisible(db.getEstVisible());
        this.setIdcommentaire(db.getIdcommentaire());
        this.setIdSpot(db.getSpotBySpotIdspot().getIdspot());
        this.setTexte(db.getTexte());
    }

    @Override
    public <E> void copyVersEntity(E entity, EntityManager em) throws Exception {
        if (em.getTransaction().isActive()) {
            dbCommentaireEntity db = (dbCommentaireEntity) entity;
            db.setEstVisible(this.getEstVisible());
            db.setTexte(db.getTexte());
            db.setSpotBySpotIdspot(em.find(dbSpotEntity.class, this.getIdSpot()));
        } else {
            logs.maTrace(Level.ERROR, MODELINTERNE_COPY_VERS_ENTITY_TXTINACT.getMessageErreur());
            throw new Exception(MODELINTERNE_COPY_VERS_ENTITY_TXTINACT.getMessageErreur());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commentaire that = (Commentaire) o;
        return idcommentaire == that.idcommentaire &&
                estVisible == that.estVisible &&
                Objects.equals(texte, that.texte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcommentaire, texte, estVisible);
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
