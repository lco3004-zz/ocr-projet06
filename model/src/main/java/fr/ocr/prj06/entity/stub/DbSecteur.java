package fr.ocr.prj06.entity.stub;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;


@Entity
@Table(name = "secteur", schema = "ocr_projet06")
public class DbSecteur implements Serializable {
    private int idsecteur;
    private String nom;
    private DbSpot spotBySpotIdspot;
    private Collection<DbVoie> voiesByIdsecteur;

    private static final long serialVersionUID=1L;

    public DbSecteur(){
        voiesByIdsecteur=new ArrayList<>();
    }

    @Id
    @Column(name = "idsecteur")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdsecteur() {
        return idsecteur;
    }

    public void setIdsecteur(int idsecteur) {
        this.idsecteur = idsecteur;
    }

    @Basic
    @Column(name = "nom", length = 45)
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
        DbSecteur that = (DbSecteur) o;
        return idsecteur == that.idsecteur &&
                Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idsecteur, nom);
    }

    @ManyToOne
    @JoinColumn(name = "spot_idspot", referencedColumnName = "idspot")
    public DbSpot getSpotBySpotIdspot() {
        return spotBySpotIdspot;
    }

    public void setSpotBySpotIdspot(DbSpot spotBySpotIdspot) {
        this.spotBySpotIdspot = spotBySpotIdspot;
    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "secteurBySecteurIdsecteur")
    public Collection<DbVoie> getVoiesByIdsecteur() {
        return voiesByIdsecteur;
    }

    public void setVoiesByIdsecteur(Collection<DbVoie> voiesByIdsecteur) {
        this.voiesByIdsecteur = voiesByIdsecteur;
    }

    @PostPersist
    public void  pp () {
        for (DbVoie dbVoie : getVoiesByIdsecteur()) {
            dbVoie.setSecteurBySecteurIdsecteur(this);
        }
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return (new StringBuilder(1024))
                .append("idSecteur: ").append(getIdsecteur())
                .append(" Nom: ").append(getNom())
                .toString();
    }
}
