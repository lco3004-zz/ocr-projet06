package fr.ocr.prj06.hibernate.stubs;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ocr_compte_utilisateur", schema = "ocr_projet06", catalog = "")
public class OcrCompteUtilisateurEntity {
    private int idocrCompteUtilisateur;
    private String ocrCompteUtilisateurMdp;
    private String ocrCompteUtilisateurLoginId;
    private String ocrCompteUtilisateurDroits;

    @Id
    @Column(name = "idocr_compte_utilisateur", nullable = false)
    public int getIdocrCompteUtilisateur()
    {
        return idocrCompteUtilisateur;
    }

    public void setIdocrCompteUtilisateur(int idocrCompteUtilisateur) {
        this.idocrCompteUtilisateur = idocrCompteUtilisateur;
    }

    @Basic
    @Column(name = "ocr_compte_utilisateur_mdp", nullable = false, length = 45)
    public String getOcrCompteUtilisateurMdp() {

        return ocrCompteUtilisateurMdp;
    }

    public void setOcrCompteUtilisateurMdp(String ocrCompteUtilisateurMdp) {
        this.ocrCompteUtilisateurMdp = ocrCompteUtilisateurMdp;
    }

    @Basic
    @Column(name = "ocr_compte_utilisateur_login_id", nullable = false, length = 45)
    public String getOcrCompteUtilisateurLoginId() {

        return ocrCompteUtilisateurLoginId;
    }

    public void setOcrCompteUtilisateurLoginId(String ocrCompteUtilisateurLoginId) {
        this.ocrCompteUtilisateurLoginId = ocrCompteUtilisateurLoginId;
    }

    @Basic
    @Column(name = "ocr_compte_utilisateur_droits", nullable = false)
    public String getOcrCompteUtilisateurDroits() {
         return ocrCompteUtilisateurDroits ;
    }

    public void setOcrCompteUtilisateurDroits(String ocrCompteUtilisateurDroits) {
        this.ocrCompteUtilisateurDroits = ocrCompteUtilisateurDroits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OcrCompteUtilisateurEntity that = (OcrCompteUtilisateurEntity) o;
        return idocrCompteUtilisateur == that.idocrCompteUtilisateur &&
                Objects.equals(ocrCompteUtilisateurMdp, that.ocrCompteUtilisateurMdp) &&
                Objects.equals(ocrCompteUtilisateurLoginId, that.ocrCompteUtilisateurLoginId) &&
                Objects.equals(ocrCompteUtilisateurDroits, that.ocrCompteUtilisateurDroits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idocrCompteUtilisateur,
                ocrCompteUtilisateurMdp,
                ocrCompteUtilisateurLoginId,
                ocrCompteUtilisateurDroits);
    }
}
