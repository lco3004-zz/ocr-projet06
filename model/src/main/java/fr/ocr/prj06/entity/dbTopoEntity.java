package fr.ocr.prj06.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Cacheable
@Table(name = "topo", schema = "ocr_projet06")
public class dbTopoEntity implements Serializable {
    private int idtopo;
    private String nom;
    private byte estPublie;
    private byte estDisponible;
    private String resume;
    private dbUserEntity userByUserIduser;

    private static final long serialVersionUID=6L;

    @Id
    @Column(name = "idtopo", nullable = false)
    public int getIdtopo() {
        return idtopo;
    }

    public void setIdtopo(int idtopo) {
        this.idtopo = idtopo;
    }

    @Basic
    @Column(name = "nom", nullable = false, length = 256)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "est_publie", nullable = false)
    public byte getEstPublie() {
        return estPublie;
    }

    public void setEstPublie(byte estPublie) {
        this.estPublie = estPublie;
    }

    @Basic
    @Column(name = "est_disponible", nullable = false)
    public byte getEstDisponible() {
        return estDisponible;
    }

    public void setEstDisponible(byte estDisponible) {
        this.estDisponible = estDisponible;
    }

    @Basic
    @Column(name = "resume", nullable = true, length = 256)
    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        dbTopoEntity that = (dbTopoEntity) o;
        return idtopo == that.idtopo &&
                estPublie == that.estPublie &&
                estDisponible == that.estDisponible &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(resume, that.resume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idtopo, nom, estPublie, estDisponible, resume);
    }

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_iduser", referencedColumnName = "iduser", nullable = false)
    public dbUserEntity getUserByUserIduser() {
        return userByUserIduser;
    }

    public void setUserByUserIduser(dbUserEntity userByUserIduser) {
        this.userByUserIduser = userByUserIduser;
    }
}
