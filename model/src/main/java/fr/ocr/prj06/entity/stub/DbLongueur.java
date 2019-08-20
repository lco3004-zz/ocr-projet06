package fr.ocr.prj06.entity.stub;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Cacheable
@Table(name = "longueur", schema = "ocr_projet06")
public class DbLongueur implements Serializable {
    private int idlongueur;
    private String nom;
    private String cotation;
    private Integer nombreDeSpits;
    private DbVoie voieByVoieIdvoie;

    private static final long serialVersionUID=3L;

    @Id
    @Column(name = "idlongueur", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        DbLongueur that = (DbLongueur) o;
        return idlongueur == that.idlongueur &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(cotation, that.cotation) &&
                Objects.equals(nombreDeSpits, that.nombreDeSpits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idlongueur, nom, cotation, nombreDeSpits);
    }

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "voie_idvoie", referencedColumnName = "idvoie", nullable = false)
    public DbVoie getVoieByVoieIdvoie() {
        return voieByVoieIdvoie;
    }

    public void setVoieByVoieIdvoie(DbVoie voieByVoieIdvoie) {
        this.voieByVoieIdvoie = voieByVoieIdvoie;
    }

    @Override
    public String toString() {
        return (new StringBuilder(1024))
                .append("idLongueur: ").append(getIdlongueur())
                .append(" Nom: ").append(getNom())
                .append(" Cotation: ").append(getCotation())
                .append(" NombreSpits: ").append(getNombreDeSpits())
                .toString();
    }

}

