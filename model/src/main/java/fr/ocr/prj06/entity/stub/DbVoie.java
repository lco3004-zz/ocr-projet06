package fr.ocr.prj06.entity.stub;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Cacheable
@Table(name = "voie", schema = "ocr_projet06")
public class DbVoie implements Serializable {
    private int idvoie;
    private String nom;
    private Collection<DbLongueur> longueursByIdvoie;
    private DbSecteur secteurBySecteurIdsecteur;

    private static final long serialVersionUID=1L;

    @Id
    @Column(name = "idvoie", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        DbVoie that = (DbVoie) o;
        return idvoie == that.idvoie &&
                Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idvoie, nom);
    }

    @OneToMany(mappedBy = "voieByVoieIdvoie")
    public Collection<DbLongueur> getLongueursByIdvoie() {
        return longueursByIdvoie;
    }

    public void setLongueursByIdvoie(Collection<DbLongueur> longueursByIdvoie) {
        this.longueursByIdvoie = longueursByIdvoie;
    }

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "secteur_idsecteur", referencedColumnName = "idsecteur", nullable = false)
    public DbSecteur getSecteurBySecteurIdsecteur() {
        return secteurBySecteurIdsecteur;
    }

    public void setSecteurBySecteurIdsecteur(DbSecteur secteurBySecteurIdsecteur) {
        this.secteurBySecteurIdsecteur = secteurBySecteurIdsecteur;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return (new StringBuilder(1024))
                .append("idVoie: ").append(getIdvoie())
                .append(" Nom: ").append(getNom())
                .toString();
    }
}
