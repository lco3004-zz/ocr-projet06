package fr.ocr.prj06.stubs;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "secteur", schema = "ocr_projet06")
public class dbSecteurEntity {
    private int idsecteur;
    private int spotIdspot;
    private String nom;
    private dbSpotEntity spotBySpotIdspot;
    private Set<dbVoieEntity> voiesByIdsecteur;

    @Id
    @Column(name = "idsecteur", nullable = false)
    public int getIdsecteur() {
        return idsecteur;
    }

    public void setIdsecteur(int idsecteur) {
        this.idsecteur = idsecteur;
    }

    @Basic
    @Column(name = "spot_idspot", nullable = false)
    public int getSpotIdspot() {
        return spotIdspot;
    }

    public void setSpotIdspot(int spotIdspot) {
        this.spotIdspot = spotIdspot;
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
                spotIdspot == that.spotIdspot &&
                Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idsecteur, spotIdspot, nom);
    }

    @ManyToOne
    @JoinColumn(name = "spot_idspot", referencedColumnName = "idspot", nullable = false)
    public dbSpotEntity getSpotBySpotIdspot() {
        return spotBySpotIdspot;
    }

    public void setSpotBySpotIdspot(dbSpotEntity spotBySpotIdspot) {
        this.spotBySpotIdspot = spotBySpotIdspot;
    }

    @OneToMany(mappedBy = "secteurBySecteurIdsecteur")
    public Set<dbVoieEntity> getVoiesByIdsecteur() {
        return voiesByIdsecteur;
    }

    public void setVoiesByIdsecteur(Set<dbVoieEntity> voiesByIdsecteur) {
        this.voiesByIdsecteur = voiesByIdsecteur;
    }
}
