package fr.ocr.prj06.hibernate.stubs;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ocr_topo", schema = "ocr_projet06", catalog = "")
public class OcrTopoEntity {
    private int idocrTopo;
    private String ocrTopoNom;
    private byte ocrTopoEstDispo;
    private OcrPratiquantEntity ocrPratiquantByOcrPratiquantIdocrPratiquant;

    @Id
    @Column(name = "idocr_topo", nullable = false)
    public int getIdocrTopo() {
        return idocrTopo;
    }

    public void setIdocrTopo(int idocrTopo) {
        this.idocrTopo = idocrTopo;
    }

    @Basic
    @Column(name = "ocr_topo_nom", nullable = true, length = 45)
    public String getOcrTopoNom() {
        return ocrTopoNom;
    }

    public void setOcrTopoNom(String ocrTopoNom) {
        this.ocrTopoNom = ocrTopoNom;
    }

    @Basic
    @Column(name = "ocr_topo_est_dispo", nullable = false)
    public byte getOcrTopoEstDispo() {
        return ocrTopoEstDispo;
    }

    public void setOcrTopoEstDispo(byte ocrTopoEstDispo) {
        this.ocrTopoEstDispo = ocrTopoEstDispo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OcrTopoEntity that = (OcrTopoEntity) o;
        return idocrTopo == that.idocrTopo &&
                ocrTopoEstDispo == that.ocrTopoEstDispo &&
                Objects.equals(ocrTopoNom, that.ocrTopoNom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idocrTopo, ocrTopoNom, ocrTopoEstDispo);
    }

    @ManyToOne
    @JoinColumn(name = "ocr_pratiquant_idocr_pratiquant", referencedColumnName = "idocr_pratiquant", nullable = false)
    public OcrPratiquantEntity getOcrPratiquantByOcrPratiquantIdocrPratiquant() {
        return ocrPratiquantByOcrPratiquantIdocrPratiquant;
    }

    public void setOcrPratiquantByOcrPratiquantIdocrPratiquant(OcrPratiquantEntity ocrPratiquantByOcrPratiquantIdocrPratiquant) {
        this.ocrPratiquantByOcrPratiquantIdocrPratiquant = ocrPratiquantByOcrPratiquantIdocrPratiquant;
    }
}
