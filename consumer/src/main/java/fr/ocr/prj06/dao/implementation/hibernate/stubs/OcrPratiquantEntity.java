package fr.ocr.prj06.dao.implementation.hibernate.stubs;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ocr_pratiquant", schema = "ocr_projet06", catalog = "")
public class OcrPratiquantEntity {
    private int idocrPratiquant;
    private String ocrPratiquantNom;
    private String ocrPratiquantEmail;
    private OcrCompteUtilisateurEntity ocrCompteUtilisateurByFkOcrCompteUtilisateurId;

    @Id
    @Column(name = "idocr_pratiquant", nullable = false)
    public int getIdocrPratiquant() {
        return idocrPratiquant;
    }

    public void setIdocrPratiquant(int idocrPratiquant) {
        this.idocrPratiquant = idocrPratiquant;
    }

    @Basic
    @Column(name = "ocr_pratiquant_nom", nullable = true, length = 45)
    public String getOcrPratiquantNom() {
        return ocrPratiquantNom;
    }

    public void setOcrPratiquantNom(String ocrPratiquantNom) {
        this.ocrPratiquantNom = ocrPratiquantNom;
    }

    @Basic
    @Column(name = "ocr_pratiquant_email", nullable = false, length = 45)
    public String getOcrPratiquantEmail() {
        return ocrPratiquantEmail;
    }

    public void setOcrPratiquantEmail(String ocrPratiquantEmail) {
        this.ocrPratiquantEmail = ocrPratiquantEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OcrPratiquantEntity that = (OcrPratiquantEntity) o;
        return idocrPratiquant == that.idocrPratiquant &&
                Objects.equals(ocrPratiquantNom, that.ocrPratiquantNom) &&
                Objects.equals(ocrPratiquantEmail, that.ocrPratiquantEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idocrPratiquant, ocrPratiquantNom, ocrPratiquantEmail);
    }

    @ManyToOne
    @JoinColumn(name = "fk_ocr_compte_utilisateur_id", referencedColumnName = "idocr_compte_utilisateur", nullable = false)
    public OcrCompteUtilisateurEntity getOcrCompteUtilisateurByFkOcrCompteUtilisateurId() {
        return ocrCompteUtilisateurByFkOcrCompteUtilisateurId;
    }

    public void setOcrCompteUtilisateurByFkOcrCompteUtilisateurId(OcrCompteUtilisateurEntity ocrCompteUtilisateurByFkOcrCompteUtilisateurId) {
        this.ocrCompteUtilisateurByFkOcrCompteUtilisateurId = ocrCompteUtilisateurByFkOcrCompteUtilisateurId;
    }
}
