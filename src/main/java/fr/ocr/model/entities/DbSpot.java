package fr.ocr.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "spot",   schema = "public", catalog = "db_projet06")
public class DbSpot implements Serializable {
    private int idspot;
    private String nom;
    private String localisation;
    private String classification;
    private Collection<DbCommentaire> commentairesByIdspot;
    private Collection<DbSecteur> secteursByIdspot;
    private DbGrimpeur grimpeurByGrimpeurIdgrimpeur;

    public DbSpot () {
        commentairesByIdspot=new ArrayList<>();
        secteursByIdspot=new ArrayList<>();
    }

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
    @Column(name = "nom", length = 256, nullable = false)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "classification", length = 64, nullable = false)
    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    @Basic
    @Column(name = "localisation", length = 256, nullable = false)
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
    @JoinColumn(name = "grimpeur_idgrimpeur", referencedColumnName = "idgrimpeur",nullable = false)
    public DbGrimpeur getGrimpeurByGrimpeurIdgrimpeur() {
        return grimpeurByGrimpeurIdgrimpeur;
    }

    public void setGrimpeurByGrimpeurIdgrimpeur(DbGrimpeur grimpeurByGrimpeurIdgrimpeur) {
        this.grimpeurByGrimpeurIdgrimpeur = grimpeurByGrimpeurIdgrimpeur;
    }

    @PostPersist
    public void  pp () {
        for ( DbCommentaire dbCommentaire: getCommentairesByIdspot()) {
            dbCommentaire.setSpotBySpotIdspot(this);
        }
        for (DbSecteur dbSecteur : getSecteursByIdspot()) {
            dbSecteur.setSpotBySpotIdspot(this);
        }
    }

    @Override
    public String toString() {
        return (new StringBuilder(1024))
                .append(" idGrimpeur: ").append(getGrimpeurByGrimpeurIdgrimpeur().getIdgrimpeur())
                .append(" idSpot: ").append(getIdspot())
                .append(" Nom: ").append(getNom())
                .append(" localisation: ").append(getLocalisation())
                .append(" Classification: ").append(getClassification())
                .toString();
    }
}
