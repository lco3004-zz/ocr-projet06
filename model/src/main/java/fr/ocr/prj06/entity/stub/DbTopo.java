package fr.ocr.prj06.entity.stub;

import fr.ocr.prj06.entity.common.EtatsResaTopo;
import fr.ocr.prj06.entity.common.JpaConvBoolInt;
import fr.ocr.prj06.entity.common.JpaConvEnumResaTopoToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "topo",   schema = "public", catalog = "projet06")
public class DbTopo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idtopo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idtopo;
    public int getIdtopo() {
        return idtopo;
    }
    public void setIdtopo(int idtopo) {
        this.idtopo = idtopo;
    }

    @Basic
    @Column(name = "nom", length = 256)
    private String nom;
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "est_publie")
    @Convert(converter = JpaConvBoolInt.class)
    private Boolean estPublie;
    public Boolean getEstPublie() {
        return estPublie;
    }
    public void setEstPublie(Boolean estPublie) {
        this.estPublie = estPublie;
    }

    @Basic
    @Column(name = "etat_reservation")
    @Convert(converter = JpaConvEnumResaTopoToString.class)
    private EtatsResaTopo etatReservation;
    public EtatsResaTopo getEtatReservation() {
        return etatReservation;
    }
    public void setEtatReservation(EtatsResaTopo etatReservation) {

        this.etatReservation = etatReservation;
    }

    @Basic
    @Column(name = "resume",  length = 256)
    private String resume;
    public String getResume() {
        return resume;
    }
    public void setResume(String resume) {
        this.resume = resume;
    }

    @Basic
    @Column(name = "lieu",  length = 45)
    private String lieu;
    public String getLieu() {
        return lieu;
    }
    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "date_de_parution")
    private Date dateDeParution;
    public Date getDateDeParution() {
        return dateDeParution;
    }
    public void setDateDeParution(Date dateDeParution) {
        this.dateDeParution = dateDeParution;
    }

    @ManyToOne
    @JoinColumn(name = "user_iduser", referencedColumnName = "iduser",nullable = false)
    private DbGrimpeur userByUserIduser;
    public DbGrimpeur getUserByUserIduser() {
        return userByUserIduser;
    }
    public void setUserByUserIduser(DbGrimpeur userByUserIduser) {
        this.userByUserIduser = userByUserIduser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DbTopo that = (DbTopo) o;
        return idtopo == that.idtopo &&
                estPublie == that.estPublie &&
                etatReservation == that.etatReservation &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(resume, that.resume) &&
                Objects.equals(lieu, that.lieu) &&
                Objects.equals(dateDeParution, that.dateDeParution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idtopo, nom, estPublie, etatReservation, resume, lieu, dateDeParution);
    }

    @Override
    public String toString() {
        return (new StringBuilder(1024))
                .append(" idUser: ").append(getUserByUserIduser().getIduser())
                .append(" idTopo: ").append(getIdtopo())
                .append(" Nom: ").append(getNom())
                .append(" EtatResa: ").append(etatReservation.toString())
                .append(" Publie: ").append(getEstPublie())
                .append(" lieu: ").append(getLieu())
                .append(" resume: ").append(getResume())
                .toString();
    }

}
