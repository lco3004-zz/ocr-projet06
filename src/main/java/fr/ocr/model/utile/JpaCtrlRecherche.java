package fr.ocr.model.utile;

import fr.ocr.model.constantes.CotationLongueur;
import fr.ocr.model.constantes.SpotClassification;

public interface JpaCtrlRecherche {
    JpaCtrlRecherche JPA_CTRL_RECHERCHE = new JpaCtrlClassRecherche_impl();

     String getNomSpot() ;
     void setNomSpot(String nomSpot) ;

     Integer getNombreSpitsLongeur() ;
     void setNombreSpitsLongeur(Integer nombreSpitsLongeur) ;

    CotationLongueur getCotationLongueur() ;
    void setCotationLongueur(CotationLongueur cotationLongueur) ;

    SpotClassification getSpotClassification() ;
    void setSpotClassification(SpotClassification spotClassification);
}

class JpaCtrlClassRecherche_impl implements JpaCtrlRecherche {
        private String nomSpot;
        private Integer nombreSpitsLongeur;
        private CotationLongueur cotationLongueur;
        private SpotClassification spotClassification;

    @Override
    public String getNomSpot() {
        return nomSpot;
    }
    @Override
    public void setNomSpot(String nomSpot) {
        this.nomSpot = nomSpot;
    }
    @Override
    public Integer getNombreSpitsLongeur() {
        return nombreSpitsLongeur;
    }
    @Override
    public void setNombreSpitsLongeur(Integer nombreSpitsLongeur) {
        this.nombreSpitsLongeur = nombreSpitsLongeur;
    }
    @Override
    public CotationLongueur getCotationLongueur() {
        return cotationLongueur;
    }
    @Override
    public void setCotationLongueur(CotationLongueur cotationLongueur) {
        this.cotationLongueur = cotationLongueur;
    }
    @Override
    public SpotClassification getSpotClassification() {
        return spotClassification;
    }
    @Override
    public void setSpotClassification(SpotClassification spotClassification) {
        this.spotClassification = spotClassification;
    }
}
