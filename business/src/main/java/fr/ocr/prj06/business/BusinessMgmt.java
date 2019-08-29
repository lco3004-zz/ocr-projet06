package fr.ocr.prj06.business;

import fr.ocr.prj06.constantes.UserProfile;
import fr.ocr.prj06.controllers.JpaCtrl;
import fr.ocr.prj06.controllers.JpaEntityManagerFactory;
import fr.ocr.prj06.entities.*;

import java.util.List;

import static fr.ocr.prj06.constantes.SpotClassification.STANDARD;

/**
 *
 */
public class BusinessMgmt {

    private JpaCtrl jpaCtrl;
    private JpaEntityManagerFactory jpaEmf;

    /* ************************************************************************************************
     * OUVERTURE/FERMETURE
     * ***********************************************************************************************
     */

    /**
     * @throws Exception
     */
    public BusinessMgmt() throws Exception {
        jpaCtrl = new JpaCtrl();
        jpaEmf = JpaEntityManagerFactory.getJpaEntityManagerFactory();
    }

    public void openDAO()  {
            jpaEmf.openDao();
    }

    public void closeDao() {

        jpaEmf.closeDao();
    }

    /* ************************************************************************************************
     * COMMENTAIREs
     * ***********************************************************************************************
     */

    /**
     *
     * @param idSpot
     * @param txtComment
     * @param isVisible
     * @return
     * @throws Exception
     */
    public DbCommentaire ajouterCommentaire(Integer idSpot, String txtComment, Boolean isVisible) throws Exception {
        return jpaCtrl.createCommentaire(idSpot, txtComment, isVisible);
    }

    /**
     *
     * @param idCommentaire
     * @throws Exception
     */
    public void supprimerCommentaire(Integer idCommentaire, Boolean doitEtreSupprimer) throws Exception {
        if (doitEtreSupprimer)
            jpaCtrl.deleteCommentaire(idCommentaire);
        else {
            jpaCtrl.archiveCommentaire(idCommentaire);
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public DbCommentaire lireCommentaire(Integer id) throws Exception {
        return jpaCtrl.readCommentaire(id);
    }

    /**
     *
     * @param idSpot
     * @return
     * @throws Exception
     */
    public List listerCommentairesActifs(Integer idSpot) throws Exception {
        return jpaCtrl.findListeCommentaires(idSpot, true, true);
    }
    /**
     *
     * @param idSpot
     * @return
     * @throws Exception
     */
    public List listerCommentairesArchives(Integer idSpot) throws Exception {
        return jpaCtrl.findListeCommentaires(idSpot, true, false);
    }

    /**
     * @param idSpot
     * @return
     * @throws Exception
     */
    public List listerTousLesCommentaires(Integer idSpot) throws Exception {
        return jpaCtrl.findListeCommentaires(idSpot, false,null);
    }

    /**
     *
     * @param idCommentaire
     * @param txtComment
     * @param isVisible
     * @return
     * @throws Exception
     */
    public DbCommentaire modiferCommentaire(Integer idCommentaire, String txtComment, Boolean isVisible) throws Exception {
        return jpaCtrl.updateCommentaire(idCommentaire, txtComment, isVisible);
    }

    /* ************************************************************************************************
     *  SPOTs
     * ***********************************************************************************************
     */

    /**
     *
     * @param idUser
     * @return
     * @throws Exception
     */
    public DbSpot ajouterSpot(Integer idUser) throws Exception {

        DbSpot dbSpot = new DbSpot();
        DbSecteur dbSecteur = new DbSecteur();
        DbVoie dbVoie = new DbVoie();
        DbLongueur dbLongueur = new DbLongueur();
        DbCommentaire dbCommentaire = new DbCommentaire();

        dbCommentaire.setEstVisible(true);
        dbCommentaire.setTexte("commentaire_par_pgm");

        dbLongueur.setNom("longueur_par_pgm");
        dbLongueur.setCotation("8a");
        dbLongueur.setNombreDeSpits(12);

        dbVoie.setNom("voie_par_pgm");

        dbSecteur.setNom("secteur_par_pgm");

        dbSpot.setClassification(STANDARD.name());
        dbSpot.setLocalisation("localisation_par_pgm");
        dbSpot.setNom("spot_par_pgm");

        dbSpot.getSecteursByIdspot().add(dbSecteur);

        dbSecteur.getVoiesByIdsecteur().add(dbVoie);

        dbVoie.getLongueursByIdvoie().add(dbLongueur);

        dbSpot.getCommentairesByIdspot().add(dbCommentaire);

        DbSpot dsp = jpaCtrl.createSpot(idUser, dbSpot);

        return dsp;
    }

    public DbGrimpeur ajouterGrimpeur(String nomGrimpeur,
                                      String emailGrimpeur,
                                      String mdpGrimpeur,
                                      UserProfile userProfile) throws Exception {
        DbGrimpeur dbGrimpeur = new DbGrimpeur();
        dbGrimpeur.setNom(nomGrimpeur);
        dbGrimpeur.setEmail(emailGrimpeur);
        dbGrimpeur.setMdp(mdpGrimpeur);
        dbGrimpeur.setProfil(userProfile);

        return jpaCtrl.createUser(dbGrimpeur);
    }

    public DbGrimpeur modifierProfilGrimpeur(Integer idUser, UserProfile userProfile) throws Exception {

        return jpaCtrl.updateUser(idUser, userProfile);
    }


}
