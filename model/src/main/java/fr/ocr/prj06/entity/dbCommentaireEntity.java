package fr.ocr.prj06.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Cacheable
@Table(name = "commentaire", schema = "ocr_projet06")
public class dbCommentaireEntity implements Serializable {
    private int idcommentaire;
    private String texte;
    private byte estVisible;
    private dbSpotEntity spotBySpotIdspot;
    private dbUserEntity userByUserIduser;

    @Id
    @Column(name = "idcommentaire", nullable = false)
    public int getIdcommentaire() {
        return idcommentaire;
    }

    public void setIdcommentaire(int idcommentaire) {
        this.idcommentaire = idcommentaire;
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
                estVisible == that.estVisible &&
                Objects.equals(texte, that.texte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcommentaire, texte, estVisible);
    }

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_idspot", referencedColumnName = "idspot", nullable = false)
    public dbSpotEntity getSpotBySpotIdspot() {
        return spotBySpotIdspot;
    }

    public void setSpotBySpotIdspot(dbSpotEntity spotBySpotIdspot) {
        this.spotBySpotIdspot = spotBySpotIdspot;
    }

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_iduser", referencedColumnName = "iduser", nullable = false)
    public dbUserEntity getUserByUserIduser() {
        return userByUserIduser;
    }

    public void setUserByUserIduser(dbUserEntity userByUserIduser) {
        this.userByUserIduser = userByUserIduser;
    }
}
