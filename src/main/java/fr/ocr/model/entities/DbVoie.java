package fr.ocr.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity

@Table(name = "voie",   schema = "public", catalog = "db_projet06")
public class DbVoie implements Serializable {
    private int idvoie;
    private String nom;
    private Collection<DbLongueur> longueursByIdvoie;
    private DbSecteur secteurBySecteurIdsecteur;

    private static final long serialVersionUID=1L;

    public DbVoie() {
        longueursByIdvoie= new ArrayList<>();
    }

    @Id
    @Column(name = "idvoie")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdvoie() {
        return idvoie;
    }

    public void setIdvoie(int idvoie) {
        this.idvoie = idvoie;
    }

    @Basic
    @Column(name = "nom", length = 64, nullable = false)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DbVoie that = (DbVoie) o;
        return idvoie == that.idvoie &&
                Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idvoie, nom);
    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "voieByVoieIdvoie")
    public Collection<DbLongueur> getLongueursByIdvoie() {
        return longueursByIdvoie;
    }

    public void setLongueursByIdvoie(Collection<DbLongueur> longueursByIdvoie) {
        this.longueursByIdvoie = longueursByIdvoie;
    }

    @ManyToOne
    @JoinColumn(name = "secteur_idsecteur", referencedColumnName = "idsecteur",nullable = false)
    public DbSecteur getSecteurBySecteurIdsecteur() {
        return secteurBySecteurIdsecteur;
    }

    public void setSecteurBySecteurIdsecteur(DbSecteur secteurBySecteurIdsecteur) {
        this.secteurBySecteurIdsecteur = secteurBySecteurIdsecteur;
    }
    @PostPersist
    public void  pp () {
        for (DbLongueur dbLongueur : getLongueursByIdvoie()) {
            dbLongueur.setVoieByVoieIdvoie(this);
        }
    }


    @Override
    public String toString() {
        return (new StringBuilder(1024))
                .append(" idSecteur: ").append(getSecteurBySecteurIdsecteur().getIdsecteur())
                .append(" idVoie: ").append(getIdvoie())
                .append(" Nom: ").append(getNom())
                .toString();
    }
}
