package fr.ocr.prj06.stubs;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "longueur", schema = "ocr_projet06")
public class dbLongueurEntity {
    private int idlongueur;
    private int voieIdvoie;
    private String nom;
    private String cotation;
    private Integer nombreDeSpits;
    private dbVoieEntity voieByVoieIdvoie;

    @Id
    @Column(name = "idlongueur", nullable = false)
    public int getIdlongueur() {
        return idlongueur;
    }

    public void setIdlongueur(int idlongueur) {
        this.idlongueur = idlongueur;
    }

    @Basic
    @Column(name = "voie_idvoie", nullable = false)
    public int getVoieIdvoie() {
        return voieIdvoie;
    }

    public void setVoieIdvoie(int voieIdvoie) {
        this.voieIdvoie = voieIdvoie;
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
                voieIdvoie == that.voieIdvoie &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(cotation, that.cotation) &&
                Objects.equals(nombreDeSpits, that.nombreDeSpits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idlongueur, voieIdvoie, nom, cotation, nombreDeSpits);
    }

    @ManyToOne
    @JoinColumn(name = "voie_idvoie", referencedColumnName = "idvoie", nullable = false)
    public dbVoieEntity getVoieByVoieIdvoie() {
        return voieByVoieIdvoie;
    }

    public void setVoieByVoieIdvoie(dbVoieEntity voieByVoieIdvoie) {
        this.voieByVoieIdvoie = voieByVoieIdvoie;
    }
}
