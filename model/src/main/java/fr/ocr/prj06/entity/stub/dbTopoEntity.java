package fr.ocr.prj06.entity.stub;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

//import java.util.Date;

@Entity
@Cacheable
@Table(name = "topo", schema = "ocr_projet06")
public class dbTopoEntity implements Serializable {
    private static final long serialVersionUID = 6L;

    private int idtopo;
    private String nom;
    private int estPublie;
    private int estDisponible;
    private String resume;
    private String lieu;
    private java.util.Date dateDeParution;
    private dbUserEntity userByUserIduser;

    @Id
    @Column(name = "idtopo", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    public int getEstPublie() {
        return estPublie;
    }

    public void setEstPublie(int estPublie) {
        this.estPublie = estPublie;
    }

    @Basic
    @Column(name = "est_disponible", nullable = false)
    public int getEstDisponible() {
        return estDisponible;
    }

    public void setEstDisponible(int estDisponible) {
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

    @Basic
    @Column(name = "lieu", nullable = false, length = 45)
    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "date_de_parution", nullable = false)
    public java.util.Date getDateDeParution() {
        return dateDeParution;
    }

    public void setDateDeParution(java.util.Date dateDeParution) {
        this.dateDeParution = dateDeParution;
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
                Objects.equals(resume, that.resume) &&
                Objects.equals(lieu, that.lieu) &&
                Objects.equals(dateDeParution, that.dateDeParution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idtopo, nom, estPublie, estDisponible, resume, lieu, dateDeParution);
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_iduser", referencedColumnName = "iduser", nullable = false)
    public dbUserEntity getUserByUserIduser() {
        return userByUserIduser;
    }

    public void setUserByUserIduser(dbUserEntity userByUserIduser) {
        this.userByUserIduser = userByUserIduser;
    }
}
