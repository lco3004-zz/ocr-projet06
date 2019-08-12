package fr.ocr.prj06.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;


@Entity
@Cacheable
@Table(name = "secteur", schema = "ocr_projet06")
public class dbSecteurEntity implements Serializable {
    private int idsecteur;
    private String nom;
    private dbSpotEntity spotBySpotIdspot;
    private Collection<dbVoieEntity> voiesByIdsecteur;

    private static final long serialVersionUID=4L;

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

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_idspot", referencedColumnName = "idspot", nullable = false)
    public dbSpotEntity getSpotBySpotIdspot() {
        return spotBySpotIdspot;
    }

    public void setSpotBySpotIdspot(dbSpotEntity spotBySpotIdspot) {
        this.spotBySpotIdspot = spotBySpotIdspot;
    }

    @OneToMany(mappedBy = "secteurBySecteurIdsecteur")
    public Collection<dbVoieEntity> getVoiesByIdsecteur() {
        return voiesByIdsecteur;
    }

    public void setVoiesByIdsecteur(Collection<dbVoieEntity> voiesByIdsecteur) {
        this.voiesByIdsecteur = voiesByIdsecteur;
    }
}
