package fr.ocr.prj06.business.spot;


import fr.ocr.prj06.constantes.SpotClassification;
import fr.ocr.prj06.controllers.JpaCtrlSpot;
import fr.ocr.prj06.entities.DbSpot;

import java.util.List;

public interface CtrlMetierSpot {
    CtrlMetierSpot CTRL_METIER_SPOT = new CtrlMetierSpot_impl();

    List<DbSpot> listerMesSpots(Integer idGrimpeur) throws Exception;
    List<DbSpot> listerTousSpots() throws Exception;
    DbSpot taggerCeSpot(Integer  idSpot) throws Exception;
}

class CtrlMetierSpot_impl implements CtrlMetierSpot {

    private JpaCtrlSpot jpaCtrlSpot;

    CtrlMetierSpot_impl() {

        this.jpaCtrlSpot = jpaCtrlSpot.JPA_CTRL_SPOT;
    }


    @Override
    public List<DbSpot> listerMesSpots(Integer idGrimpeur) throws Exception {
        return jpaCtrlSpot.findListeSpots(idGrimpeur);
    }

    @Override
    public List<DbSpot> listerTousSpots() throws Exception {
        return jpaCtrlSpot.findListeSpots(null);

    }

    @Override
    public DbSpot taggerCeSpot(Integer idSpot) throws Exception {
        DbSpot dbSpot = jpaCtrlSpot.readSpot(idSpot);
        dbSpot.setClassification(SpotClassification.OFFICIEL.name());
        return jpaCtrlSpot.updateSpot(dbSpot);
    }
}