package fr.ocr.prj06.stubs;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "spot", schema = "ocr_projet06")
public class dbSpotEntity {
    private int idspot;
    private int utilisateurIdUtilsateur;
    private String nom;
    private String localisation;
    private Set<dbCommentaireEntity> commentairesByIdspot;
    private Set<dbSecteurEntity> secteursByIdspot;
    private dbUtilisateurEntity utilisateurByUtilisateurIdUtilsateur;

    @Id
    @Column(name = "idspot", nullable = false)
    public int getIdspot() {
        return idspot;
    }

    public void setIdspot(int idspot) {
        this.idspot = idspot;
    }

    @Basic
    @Column(name = "utilisateur_id_utilsateur", nullable = false)
    public int getUtilisateurIdUtilsateur() {
        return utilisateurIdUtilsateur;
    }

    public void setUtilisateurIdUtilsateur(int utilisateurIdUtilsateur) {
        this.utilisateurIdUtilsateur = utilisateurIdUtilsateur;
    }

    @Basic
    @Column(name = "nom", nullable = false, length = 45)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "localisation", nullable = false, length = 45)
    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        dbSpotEntity that = (dbSpotEntity) o;
        return idspot == that.idspot &&
                utilisateurIdUtilsateur == that.utilisateurIdUtilsateur &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(localisation, that.localisation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idspot, utilisateurIdUtilsateur, nom, localisation);
    }

    @OneToMany(mappedBy = "spotBySpotIdspot")
    public Set<dbCommentaireEntity> getCommentairesByIdspot() {
        return commentairesByIdspot;
    }

    public void setCommentairesByIdspot(Set<dbCommentaireEntity> commentairesByIdspot) {
        this.commentairesByIdspot = commentairesByIdspot;
    }

    @OneToMany(mappedBy = "spotBySpotIdspot")
    public Set<dbSecteurEntity> getSecteursByIdspot() {
        return secteursByIdspot;
    }

    public void setSecteursByIdspot(Set<dbSecteurEntity> secteursByIdspot) {
        this.secteursByIdspot = secteursByIdspot;
    }

    @ManyToOne
    @JoinColumn(name = "utilisateur_id_utilsateur", referencedColumnName = "id_utilsateur", nullable = false)
    public dbUtilisateurEntity getUtilisateurByUtilisateurIdUtilsateur() {
        return utilisateurByUtilisateurIdUtilsateur;
    }

    public void setUtilisateurByUtilisateurIdUtilsateur(dbUtilisateurEntity utilisateurByUtilisateurIdUtilsateur) {
        this.utilisateurByUtilisateurIdUtilsateur = utilisateurByUtilisateurIdUtilsateur;
    }
}
