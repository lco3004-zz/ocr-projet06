package fr.ocr.prj06.stubs;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "secteur", schema = "ocr_projet06")

public class dbSecteurEntity {
    private int idsecteur;
    private String nom;

    @Id
    @Column(name = "idsecteur", nullable = false)
    public int getIdsecteur() {
        return idsecteur;
    }

    public void setIdsecteur(int idsecteur) {
        this.idsecteur = idsecteur;
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
        dbSecteurEntity that = (dbSecteurEntity) o;
        return idsecteur == that.idsecteur &&
                Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idsecteur, nom);
    }
}
