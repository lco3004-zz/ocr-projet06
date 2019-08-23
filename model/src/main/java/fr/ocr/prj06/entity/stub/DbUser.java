package fr.ocr.prj06.entity.stub;


import fr.ocr.prj06.entity.common.JpaConvEnumUserToString;
import fr.ocr.prj06.entity.common.UserProfile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "ocr_projet06")
public class DbUser implements Serializable {
    private static final long serialVersionUID = 1L;

    private int iduser;
    private String nom;
    private String email;
    private String mdp;


    private Collection<DbSpot> spotsByIduser;
    private Collection<DbTopo> toposByIduser;

    @Id
    @Column(name = "iduser")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    @Basic
    @Column(name = "nom", length = 256)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "email",  length = 256)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "mdp",  length = 16)
    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Basic
    @Column(name = "profil", length = 2)
    @Convert(converter = JpaConvEnumUserToString.class)
    private UserProfile profil;

    public UserProfile getProfil() {
        return profil;
    }

    public void setProfil(UserProfile profil) {
        this.profil = profil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DbUser that = (DbUser) o;
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

    @OneToMany(cascade =CascadeType.ALL, mappedBy = "userByUserIduser")
    public Collection<DbSpot> getSpotsByIduser() {
        return spotsByIduser;
    }

    public void setSpotsByIduser(Collection<DbSpot> spotsByIduser) {
        this.spotsByIduser = spotsByIduser;
    }

    @OneToMany(cascade =CascadeType.ALL,mappedBy = "userByUserIduser")
    public Collection<DbTopo> getToposByIduser() {
        return toposByIduser;
    }

    public void setToposByIduser(Collection<DbTopo> toposByIduser) {
        this.toposByIduser = toposByIduser;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return (new StringBuilder(1024))
                .append("idUser: ").append(getIduser())
                .append(" Nom: ").append(getNom())
                .append(" Mdp: ").append(getMdp())
                .append(" Profil: ").append(getProfil().toString())
                .append(" email: ").append(getEmail())
                .toString();
    }

}
