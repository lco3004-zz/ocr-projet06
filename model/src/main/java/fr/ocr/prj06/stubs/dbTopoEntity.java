package fr.ocr.prj06.stubs;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "topo", schema = "ocr_projet06")
public class dbTopoEntity {
    private int idTopo;
    private int utilisateurIdUtilsateur;
    private String nom;
    private byte estPublie;
    private byte estDisponible;
    private String resume;
    private dbUtilisateurEntity utilisateurByUtilisateurIdUtilsateur;

    @Id
    @Column(name = "id_topo", nullable = false)
    public int getIdTopo() {
        return idTopo;
    }

    public void setIdTopo(int idTopo) {
        this.idTopo = idTopo;
    }

    @Basic
    @Column(name = "utilisateur_id_utilsateur", nullable = false)
    public int getUtilisateurIdUtilsateur() {
        return utilisateurIdUtilsateur;
    }

    public void setUtilisateurIdUtilsateur(int utilisateurIdUtilsateur) {
        this.utilisateurIdUtilsateur = utilisateurIdUtilsateur;
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
        return idTopo == that.idTopo &&
                utilisateurIdUtilsateur == that.utilisateurIdUtilsateur &&
                estPublie == that.estPublie &&
                estDisponible == that.estDisponible &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(resume, that.resume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTopo, utilisateurIdUtilsateur, nom, estPublie, estDisponible, resume);
    }

    @ManyToOne
    @JoinColumn(name = "utilisateur_id_utilsateur", referencedColumnName = "id_utilsateur", nullable = false)
    public dbUtilisateurEntity getUtilisateurByUtilisateurIdUtilsateur() {
        return utilisateurByUtilisateurIdUtilsateur;
    }

    public void setUtilisateurByUtilisateurIdUtilsateur(dbUtilisateurEntity utilisateurByUtilisateurIdUtilsateur) {
        this.utilisateurByUtilisateurIdUtilsateur = utilisateurByUtilisateurIdUtilsateur;
    }
}
