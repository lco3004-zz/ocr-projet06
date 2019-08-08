package fr.ocr.prj06.stubs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "ocr_projet06")
public class dbUserEntity implements Serializable {
    private int iduser;
    private String nom;
    private String email;
    private Collection<dbCommentaireEntity> commentairesByIduser;
    private dbCpteEntity cpteByIduser;
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
        dbUserEntity that = (dbUserEntity) o;
        return iduser == that.iduser &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iduser, nom, email);
    }

    @OneToMany(mappedBy = "userByUserIduser")
    public Collection<dbCommentaireEntity> getCommentairesByIduser() {
        return commentairesByIduser;
    }

    public void setCommentairesByIduser(Collection<dbCommentaireEntity> commentairesByIduser) {
        this.commentairesByIduser = commentairesByIduser;
    }

    @OneToOne(mappedBy = "userByUserIduser", fetch = FetchType.LAZY)
    public dbCpteEntity getCpteByIduser() {
        return cpteByIduser;
    }

    public void setCpteByIduser(dbCpteEntity cpteByIduser) {
        this.cpteByIduser = cpteByIduser;
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
