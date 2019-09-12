package fr.ocr.model.entities;

import fr.ocr.model.constantes.EtatsResaTopo;
import fr.ocr.model.converters.JpaConvBoolInt;
import fr.ocr.model.converters.JpaConvEnumResaTopoToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "topo",   schema = "public", catalog = "db_projet06")
public class DbTopo implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idtopo;
    private Integer idEmprunteur;
    private String nom;
    private String lieu;
    private String resume;
    private Date dateDeParution;
    private Boolean estPublie;
    private EtatsResaTopo etatReservation;
    private DbGrimpeur grimpeurByGrimpeurIdgrimpeur;

    @Id
    @Column(name = "idtopo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdtopo() {
        return idtopo;
    }
    public void setIdtopo(int idtopo) {
        this.idtopo = idtopo;
    }

    @Basic
    @Column(name = "nom", length = 256, nullable = false)
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "est_publie", nullable = false)
    @Convert(converter = JpaConvBoolInt.class)
    public Boolean getEstPublie() {
        return estPublie;
    }
    public void setEstPublie(Boolean estPublie) {
        this.estPublie = estPublie;
    }

    @Basic
    @Column(name = "etat_reservation", nullable = false)
    @Convert(converter = JpaConvEnumResaTopoToString.class)
    public EtatsResaTopo getEtatReservation() {
        return etatReservation;
    }

    public void setEtatReservation(EtatsResaTopo etatReservation) {
        this.etatReservation = etatReservation;
    }

    @Basic
    @Column(name = "resume",  length = 256)
    public String getResume() {
        return resume;
    }
    public void setResume(String resume) {
        this.resume = resume;
    }

    @Basic
    @Column(name = "lieu", length = 256, nullable = false)
    public String getLieu() {
        return lieu;
    }
    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Basic
    @Column(name = "idemprunteur")
    public Integer getIdEmprunteur() {
        return idEmprunteur;
    }

    public void setIdEmprunteur(Integer idEmprunteur) {
        this.idEmprunteur = idEmprunteur;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "date_de_parution", nullable = false)
    public Date getDateDeParution() {
        return dateDeParution;
    }
    public void setDateDeParution(Date dateDeParution) {
        this.dateDeParution = dateDeParution;
    }

    @ManyToOne
    @JoinColumn(name = "grimpeur_idgrimpeur", referencedColumnName = "idgrimpeur",nullable = false)
    public DbGrimpeur getGrimpeurByGrimpeurIdgrimpeur() {
        return grimpeurByGrimpeurIdgrimpeur;
    }
    public void setGrimpeurByGrimpeurIdgrimpeur(  DbGrimpeur grimpeurByGrimpeurIdgrimpeur) {
        this.grimpeurByGrimpeurIdgrimpeur = grimpeurByGrimpeurIdgrimpeur;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DbTopo dbTopo = (DbTopo) o;

        if (idtopo != dbTopo.idtopo) return false;
        if (estPublie != dbTopo.estPublie) return false;
        if (dateDeParution != null ? !dateDeParution.equals(dbTopo.dateDeParution) : dbTopo.dateDeParution != null)
            return false;
        if (etatReservation != null ? !etatReservation.equals(dbTopo.etatReservation) : dbTopo.etatReservation != null)
            return false;
        if (lieu != null ? !lieu.equals(dbTopo.lieu) : dbTopo.lieu != null) return false;
        if (nom != null ? !nom.equals(dbTopo.nom) : dbTopo.nom != null) return false;
        if (resume != null ? !resume.equals(dbTopo.resume) : dbTopo.resume != null) return false;
        return idEmprunteur != null ? idEmprunteur.equals(dbTopo.idEmprunteur) : dbTopo.idEmprunteur == null;
    }

    @Override
    public int hashCode() {
        int result = idtopo;
        result = 31 * result + (dateDeParution != null ? dateDeParution.hashCode() : 0);
        result = 31 * result + (estPublie != null ? estPublie.hashCode() : 0);
        result = 31 * result + (etatReservation != null ? etatReservation.hashCode() : 0);
        result = 31 * result + (lieu != null ? lieu.hashCode() : 0);
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (resume != null ? resume.hashCode() : 0);
        result = 31 * result + (idEmprunteur != null ? idEmprunteur.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return (new StringBuilder(1024))
                .append(" idGrimpeur: ").append(getGrimpeurByGrimpeurIdgrimpeur().getIdgrimpeur())
                .append("idEmprunteur: ").append(idEmprunteur)
                .append(" idTopo: ").append(getIdtopo())
                .append(" Nom: ").append(getNom())
                .append(" EtatResa: ").append(etatReservation.toString())
                .append(" Publie: ").append(getEstPublie())
                .append(" Lieu: ").append(getLieu())
                .append(" Resumé: ").append(getResume())
                .toString();
    }

}
