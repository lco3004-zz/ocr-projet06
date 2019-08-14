package fr.ocr.prj06.entity.stub;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "ocr_projet06")
public class dbUserEntity implements Serializable {
    private static final long serialVersionUID = 7L;

    private int iduser;
    private String nom;
    private String email;
    private String mdp;
    private String profil;
    private Collection<dbCommentaireEntity> commentairesByIduser;
    private Collection<dbSpotEntity> spotsByIduser;
    private Collection<dbTopoEntity> toposByIduser;

    @Id
    @Column(name = "iduser", nullable = false)
    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    @Basic
    @Column(name = "nom", nullable = true, length = 256)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 256)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "mdp", nullable = false, length = 16)
    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Basic
    @Column(name = "profil", nullable = false, length = 2)
    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        dbUserEntity that = (dbUserEntity) o;
        return iduser == that.iduser &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(email, that.email) &&
                Objects.equals(mdp, that.mdp) &&
                Objects.equals(profil, that.profil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iduser, nom, email, mdp, profil);
    }

    @OneToMany(mappedBy = "userByUserIduser")
    public Collection<dbCommentaireEntity> getCommentairesByIduser() {
        return commentairesByIduser;
    }

    public void setCommentairesByIduser(Collection<dbCommentaireEntity> commentairesByIduser) {
        this.commentairesByIduser = commentairesByIduser;
    }

    @OneToMany(mappedBy = "userByUserIduser")
    public Collection<dbSpotEntity> getSpotsByIduser() {
        return spotsByIduser;
    }

    public void setSpotsByIduser(Collection<dbSpotEntity> spotsByIduser) {
        this.spotsByIduser = spotsByIduser;
    }

    @OneToMany(mappedBy = "userByUserIduser")
    public Collection<dbTopoEntity> getToposByIduser() {
        return toposByIduser;
    }

    public void setToposByIduser(Collection<dbTopoEntity> toposByIduser) {
        this.toposByIduser = toposByIduser;
    }
}
