package fr.ocr.prj06.entity.stub;

import fr.ocr.prj06.entity.common.JpaConvBoolInt;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

//import java.util.Date;

@Entity
@Cacheable
@Table(name = "topo", schema = "ocr_projet06")
public class DbTopo implements Serializable {
    private static final long serialVersionUID = 6L;

    private int idtopo;
    private String nom;
    @Convert(converter = JpaConvBoolInt.class)
    private Boolean estPublie;
    @Convert(converter = JpaConvBoolInt.class)
    private Boolean estDisponible;
    private String resume;
    private String lieu;
    private java.util.Date dateDeParution;
    private DbUser userByUserIduser;

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
    public Boolean getEstPublie() {
        return estPublie;
    }

    public void setEstPublie(Boolean estPublie) {
        this.estPublie = estPublie;
    }

    @Basic
    @Column(name = "est_disponible", nullable = false)
    public Boolean getEstDisponible() {
        return estDisponible;
    }

    public void setEstDisponible(Boolean estDisponible) {
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
        DbTopo that = (DbTopo) o;
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
    public DbUser getUserByUserIduser() {
        return userByUserIduser;
    }

    public void setUserByUserIduser(DbUser userByUserIduser) {
        this.userByUserIduser = userByUserIduser;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return (new StringBuilder(1024))
                .append("idTopo: ").append(getIdtopo())
                .append(" Nom: ").append(getNom())
                .append(" Disponible: ").append(getEstDisponible())
                .append(" Publie: ").append(getEstPublie())
                .append(" lieu: ").append(getLieu())
                .append(" resume: ").append(getResume())
                .toString();
    }

}
