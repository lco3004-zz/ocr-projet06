package fr.ocr.prj06.stubs;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "longueur", schema = "ocr_projet06")
public class dbLongueurEntity {
    private int idlongueur;
    private String nom;
    private String cotation;
    private Integer nombreDeSpits;

    @Id
    @Column(name = "idlongueur", nullable = false)
    public int getIdlongueur() {
        return idlongueur;
    }

    public void setIdlongueur(int idlongueur) {
        this.idlongueur = idlongueur;
    }

    @Basic
    @Column(name = "nom", nullable = true, length = 45)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "cotation", nullable = true, length = 45)
    public String getCotation() {
        return cotation;
    }

    public void setCotation(String cotation) {
        this.cotation = cotation;
    }

    @Basic
    @Column(name = "nombre_de_spits", nullable = true)
    public Integer getNombreDeSpits() {
        return nombreDeSpits;
    }

    public void setNombreDeSpits(Integer nombreDeSpits) {
        this.nombreDeSpits = nombreDeSpits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        dbLongueurEntity that = (dbLongueurEntity) o;
        return idlongueur == that.idlongueur &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(cotation, that.cotation) &&
                Objects.equals(nombreDeSpits, that.nombreDeSpits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idlongueur, nom, cotation, nombreDeSpits);
    }
}
