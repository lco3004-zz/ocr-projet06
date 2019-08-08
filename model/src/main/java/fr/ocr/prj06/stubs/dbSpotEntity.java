package fr.ocr.prj06.stubs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Cacheable
@Table(name = "spot", schema = "ocr_projet06")
public class dbSpotEntity implements Serializable {
    private int idspot;
    private String nom;
    private String localisation;
    private Collection<dbCommentaireEntity> commentairesByIdspot;
    private Collection<dbSecteurEntity> secteursByIdspot;
    private dbUserEntity userByUserIduser;

    @Id
    @Column(name = "idspot", nullable = false)
    public int getIdspot() {
        return idspot;
    }

    public void setIdspot(int idspot) {
        this.idspot = idspot;
    }

    @Basic
    @Column(name = "nom", nullable = false, length = 45)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "localisation", nullable = false, length = 45)
    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        dbSpotEntity that = (dbSpotEntity) o;
        return idspot == that.idspot &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(localisation, that.localisation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idspot, nom, localisation);
    }

    @OneToMany(mappedBy = "spotBySpotIdspot")
    public Collection<dbCommentaireEntity> getCommentairesByIdspot() {
        return commentairesByIdspot;
    }

    public void setCommentairesByIdspot(Collection<dbCommentaireEntity> commentairesByIdspot) {
        this.commentairesByIdspot = commentairesByIdspot;
    }

    @OneToMany(mappedBy = "spotBySpotIdspot")
    public Collection<dbSecteurEntity> getSecteursByIdspot() {
        return secteursByIdspot;
    }

    public void setSecteursByIdspot(Collection<dbSecteurEntity> secteursByIdspot) {
        this.secteursByIdspot = secteursByIdspot;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_iduser", referencedColumnName = "iduser", nullable = false)
    public dbUserEntity getUserByUserIduser() {
        return userByUserIduser;
    }

    public void setUserByUserIduser(dbUserEntity userByUserIduser) {
        this.userByUserIduser = userByUserIduser;
    }
}
