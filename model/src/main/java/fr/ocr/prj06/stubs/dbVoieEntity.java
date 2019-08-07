package fr.ocr.prj06.stubs;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "voie", schema = "ocr_projet06")
public class dbVoieEntity {
    private int idvoie;
    private String nom;
    private dbSecteurEntity secteurBySecteurIdsecteur;

    @Id
    @Column(name = "idvoie", nullable = false)
    public int getIdvoie() {
        return idvoie;
    }

    public void setIdvoie(int idvoie) {
        this.idvoie = idvoie;
    }

    @Basic
    @Column(name = "nom", nullable = false, length = 45)
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
        dbVoieEntity that = (dbVoieEntity) o;
        return idvoie == that.idvoie &&
                Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idvoie, nom);
    }

    @ManyToOne
    @JoinColumn(name = "secteur_idsecteur", referencedColumnName = "idsecteur", nullable = false)
    public dbSecteurEntity getSecteurBySecteurIdsecteur() {
        return secteurBySecteurIdsecteur;
    }

    public void setSecteurBySecteurIdsecteur(dbSecteurEntity secteurBySecteurIdsecteur) {
        this.secteurBySecteurIdsecteur = secteurBySecteurIdsecteur;
    }
}
