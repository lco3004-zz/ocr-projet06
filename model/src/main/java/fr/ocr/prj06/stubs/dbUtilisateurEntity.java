package fr.ocr.prj06.stubs;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "utilisateur", schema = "ocr_projet06", catalog = "")
public class dbUtilisateurEntity {
    private int idUtilsateur;
    private String nom;
    private String email;
    private dbCompteUtilisateurEntity compteUtilisateurByFkCompteUtilisateurId;

    @Id
    @Column(name = "id_utilsateur", nullable = false)
    public int getIdUtilsateur() {
        return idUtilsateur;
    }

    public void setIdUtilsateur(int idUtilsateur) {
        this.idUtilsateur = idUtilsateur;
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
    @Column(name = "email", nullable = false, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        dbUtilisateurEntity that = (dbUtilisateurEntity) o;
        return idUtilsateur == that.idUtilsateur &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUtilsateur, nom, email);
    }

    @ManyToOne
    @JoinColumn(name = "fk_compte_utilisateur_id", referencedColumnName = "id_compte", nullable = false)
    public dbCompteUtilisateurEntity getCompteUtilisateurByFkCompteUtilisateurId() {
        return compteUtilisateurByFkCompteUtilisateurId;
    }

    public void setCompteUtilisateurByFkCompteUtilisateurId(dbCompteUtilisateurEntity compteUtilisateurByFkCompteUtilisateurId) {
        this.compteUtilisateurByFkCompteUtilisateurId = compteUtilisateurByFkCompteUtilisateurId;
    }
}
