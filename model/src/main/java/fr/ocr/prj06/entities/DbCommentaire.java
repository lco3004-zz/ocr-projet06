package fr.ocr.prj06.entities;

import fr.ocr.prj06.converters.JpaConvBoolInt;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "commentaire", schema = "public", catalog = "db_projet06")
public class DbCommentaire implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idcommentaire;
    private String texte;

    @Convert(converter = JpaConvBoolInt.class)
    private Boolean estVisible;

    private DbSpot spotBySpotIdspot;

    @Id
    @Column(name = "idcommentaire", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdcommentaire() {
        return idcommentaire;
    }

    public void setIdcommentaire(int idcommentaire) {
        this.idcommentaire = idcommentaire;
    }

    @Basic
    @Column(name = "texte", length = 256)
    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    @Basic
    @Column(name = "est_visible")
    public Boolean getEstVisible() {
        return estVisible;
    }

    public void setEstVisible(Boolean estVisible) {
        this.estVisible = estVisible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DbCommentaire that = (DbCommentaire) o;
        return idcommentaire == that.idcommentaire &&
                estVisible == that.estVisible &&
                Objects.equals(texte, that.texte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcommentaire, texte, estVisible);
    }

    @ManyToOne
    @JoinColumn(name = "spot_idspot", referencedColumnName = "idspot")
    public DbSpot getSpotBySpotIdspot() {
        return spotBySpotIdspot;
    }

    public void setSpotBySpotIdspot(DbSpot spotBySpotIdspot) {
        this.spotBySpotIdspot = spotBySpotIdspot;
    }


    @Override
    public String toString() {
        return (new StringBuilder(1024))
                .append(" idSpot: ").append(getSpotBySpotIdspot().getIdspot())
                .append(" idCommentaire: ").append(getIdcommentaire())
                .append(" estVisible: ").append(getEstVisible())
                .append(" texte: ").append(getTexte())
                .toString();
    }
}
