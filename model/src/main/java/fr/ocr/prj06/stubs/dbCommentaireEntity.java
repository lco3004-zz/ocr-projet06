package fr.ocr.prj06.stubs;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "commentaire", schema = "ocr_projet06")
public class dbCommentaireEntity {
    private int idcommentaire;
    private int spotIdspot;
    private String texte;
    private byte estVisible;
    private dbSpotEntity spotBySpotIdspot;

    @Id
    @Column(name = "idcommentaire", nullable = false)
    public int getIdcommentaire() {
        return idcommentaire;
    }

    public void setIdcommentaire(int idcommentaire) {
        this.idcommentaire = idcommentaire;
    }

    @Basic
    @Column(name = "spot_idspot", nullable = false)
    public int getSpotIdspot() {
        return spotIdspot;
    }

    public void setSpotIdspot(int spotIdspot) {
        this.spotIdspot = spotIdspot;
    }

    @Basic
    @Column(name = "texte", nullable = true, length = 256)
    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    @Basic
    @Column(name = "est_visible", nullable = false)
    public byte getEstVisible() {
        return estVisible;
    }

    public void setEstVisible(byte estVisible) {
        this.estVisible = estVisible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        dbCommentaireEntity that = (dbCommentaireEntity) o;
        return idcommentaire == that.idcommentaire &&
                spotIdspot == that.spotIdspot &&
                estVisible == that.estVisible &&
                Objects.equals(texte, that.texte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcommentaire, spotIdspot, texte, estVisible);
    }

    @ManyToOne
    @JoinColumn(name = "spot_idspot", referencedColumnName = "idspot", nullable = false)
    public dbSpotEntity getSpotBySpotIdspot() {
        return spotBySpotIdspot;
    }

    public void setSpotBySpotIdspot(dbSpotEntity spotBySpotIdspot) {
        this.spotBySpotIdspot = spotBySpotIdspot;
    }
}
