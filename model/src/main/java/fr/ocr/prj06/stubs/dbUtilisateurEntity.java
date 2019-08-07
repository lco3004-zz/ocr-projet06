package fr.ocr.prj06.stubs;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "utilisateur", schema = "ocr_projet06")
public class dbUtilisateurEntity {
    private int idUtilsateur;
    private int compteUtilisateurIdCompte;
    private String nom;
    private String email;
    private Set<dbSpotEntity> spotsByIdUtilsateur;
    private Set<dbTopoEntity> toposByIdUtilsateur;
    private dbCompteUtilisateurEntity compteUtilisateurByCompteUtilisateurIdCompte;

    @Id
    @Column(name = "id_utilsateur", nullable = false)
    public int getIdUtilsateur() {
        return idUtilsateur;
    }

    public void setIdUtilsateur(int idUtilsateur) {
        this.idUtilsateur = idUtilsateur;
    }

    @Basic
    @Column(name = "compte_utilisateur_id_compte", nullable = false)
    public int getCompteUtilisateurIdCompte() {
        return compteUtilisateurIdCompte;
    }

    public void setCompteUtilisateurIdCompte(int compteUtilisateurIdCompte) {
        this.compteUtilisateurIdCompte = compteUtilisateurIdCompte;
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
                compteUtilisateurIdCompte == that.compteUtilisateurIdCompte &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUtilsateur, compteUtilisateurIdCompte, nom, email);
    }

    @OneToMany(mappedBy = "utilisateurByUtilisateurIdUtilsateur")
    public Set<dbSpotEntity> getSpotsByIdUtilsateur() {
        return spotsByIdUtilsateur;
    }

    public void setSpotsByIdUtilsateur(Set<dbSpotEntity> spotsByIdUtilsateur) {
        this.spotsByIdUtilsateur = spotsByIdUtilsateur;
    }

    @OneToMany(mappedBy = "utilisateurByUtilisateurIdUtilsateur")
    public Set<dbTopoEntity> getToposByIdUtilsateur() {
        return toposByIdUtilsateur;
    }

    public void setToposByIdUtilsateur(Set<dbTopoEntity> toposByIdUtilsateur) {
        this.toposByIdUtilsateur = toposByIdUtilsateur;
    }

    @OneToOne
    @JoinColumn(name = "compte_utilisateur_id_compte", referencedColumnName = "id_compte", nullable = false)
    public dbCompteUtilisateurEntity getCompteUtilisateurByCompteUtilisateurIdCompte() {
        return compteUtilisateurByCompteUtilisateurIdCompte;
    }

    public void setCompteUtilisateurByCompteUtilisateurIdCompte(dbCompteUtilisateurEntity compteUtilisateurByCompteUtilisateurIdCompte) {
        this.compteUtilisateurByCompteUtilisateurIdCompte = compteUtilisateurByCompteUtilisateurIdCompte;
    }
}
