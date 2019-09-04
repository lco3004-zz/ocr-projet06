package fr.ocr.prj06.business;

import fr.ocr.prj06.controllers.JpaCtrlSpot;
import fr.ocr.prj06.controllers.JpaEntityManagerFactory;
import fr.ocr.prj06.entities.*;

import static fr.ocr.prj06.constantes.CotationLongueur.SIX_A;
import static fr.ocr.prj06.constantes.SpotClassification.STANDARD;

/**
 *
 */
public class BusinessMgmt {
    private JpaCtrlSpot jpaCtrl;
    private JpaEntityManagerFactory jpaEmf;

    /* ************************************************************************************************
     * OUVERTURE/FERMETURE
     * ***********************************************************************************************
     */

    public BusinessMgmt() throws Exception {
        jpaCtrl = JpaCtrlSpot.JPA_CTRL_SPOT;

        jpaEmf = JpaEntityManagerFactory.getJpaEntityManagerFactory();
    }

    public void openDAO()  {

        jpaEmf.openDao();
    }

    public void closeDao() {

        jpaEmf.closeDao();
    }
    public DbSpot ajouterSpot(Integer idUser) throws Exception {

        DbSpot dbSpot = new DbSpot();
        DbSecteur dbSecteur = new DbSecteur();
        DbVoie dbVoie = new DbVoie();
        DbLongueur dbLongueur = new DbLongueur();
        DbCommentaire dbCommentaire = new DbCommentaire();

        dbCommentaire.setEstVisible(true);
        dbCommentaire.setTexte("commentaire_par_pgm");

        dbLongueur.setNom("longueur_par_pgm");
        dbLongueur.setCotation(SIX_A);
        dbLongueur.setNombreDeSpits(12);

        dbVoie.setNom("voie_par_pgm");
        dbVoie.getLongueursByIdvoie().add(dbLongueur);

        dbSecteur.setNom("secteur_par_pgm");
        dbSecteur.getVoiesByIdsecteur().add(dbVoie);

        dbSpot.setClassification(STANDARD.name());
        dbSpot.setLocalisation("localisation_par_pgm");
        dbSpot.setNom("spot_par_pgm");
        dbSpot.getSecteursByIdspot().add(dbSecteur);
        dbSpot.getCommentairesByIdspot().add(dbCommentaire);

        DbSpot dsp = jpaCtrl.createSpot(idUser, dbSpot);

        return dsp;
    }

}
