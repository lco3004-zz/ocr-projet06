package fr.ocr.prj06.entity.stub;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "spot", schema = "ocr_projet06")
public class DbSpot implements Serializable {
    private int idspot;
    private String nom;
    private String localisation;
    private String classification;
    private Collection<DbCommentaire> commentairesByIdspot;
    private Collection<DbSecteur> secteursByIdspot;
    private DbUser userByUserIduser;

    private static final long serialVersionUID=1L;

    @Id
    @Column(name = "idspot")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdspot() {
        return idspot;
    }

    public void setIdspot(int idspot) {
        this.idspot = idspot;
    }

    @Basic
    @Column(name = "nom", length = 45)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "classification", length = 45)
    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    @Basic
    @Column(name = "localisation", length = 45)
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
        DbSpot that = (DbSpot) o;
        return idspot == that.idspot &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(localisation, that.localisation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idspot, nom, localisation);
    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "spotBySpotIdspot")
    public Collection<DbCommentaire> getCommentairesByIdspot() {
        return commentairesByIdspot;
    }

    public void setCommentairesByIdspot(Collection<DbCommentaire> commentairesByIdspot) {
        this.commentairesByIdspot = commentairesByIdspot;
    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "spotBySpotIdspot")
    public Collection<DbSecteur> getSecteursByIdspot() {
        return secteursByIdspot;
    }

    public void setSecteursByIdspot(Collection<DbSecteur> secteursByIdspot) {
        this.secteursByIdspot = secteursByIdspot;
    }

    @ManyToOne
    @JoinColumn(name = "user_iduser", referencedColumnName = "iduser")
    public DbUser getUserByUserIduser() {
        return userByUserIduser;
    }

    public void setUserByUserIduser(DbUser userByUserIduser) {
        this.userByUserIduser = userByUserIduser;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return (new StringBuilder(1024))
                .append("idSpot: ").append(getIdspot())
                .append(" Nom: ").append(getNom())
                .append(" localisation: ").append(getLocalisation())
                .append(" Classification: ").append(getClassification())
                .toString();
    }
}
