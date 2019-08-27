package fr.ocr.prj06.business;

import fr.ocr.prj06.controller.JpaCtrl;
import fr.ocr.prj06.entity.common.EtatsResaTopo;
import fr.ocr.prj06.entity.common.JpaEmfInterface;
import fr.ocr.prj06.entity.common.UserProfile;
import fr.ocr.prj06.entity.stub.*;

import java.sql.Date;
import java.util.List;

import static fr.ocr.prj06.entity.common.JpaEmfUtility.getInstanceEMF;
import static fr.ocr.prj06.entity.common.SpotClassification.STANDARD;

/**
 *
 */
public class BusinessMgmt {

    private JpaCtrl jpaCtrl;
    private JpaEmfInterface jpaEmfInterface;

    /* ************************************************************************************************
     * OUVERTURE/FERMETURE
     * ***********************************************************************************************
     */

    /**
     * @throws Exception
     */
    public BusinessMgmt() throws Exception {
        jpaCtrl = new JpaCtrl();
        jpaEmfInterface = getInstanceEMF();
    }

    /**
     *
     * @throws Exception
     */
    public void openDAO()  {
            jpaEmfInterface.openDao();
    }

    /**
     *
     * @throws Exception
     */
    public void closeDao() {
            jpaEmfInterface.closeDao();
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
    * TOPOs
    * ***********************************************************************************************
    */
    public Iterable<? extends DbTopo> listerTousTopos() throws Exception {
        return jpaCtrl.findListeTopos(null );
    }
    /**
     * @param idUser
     * @return
     * @throws Exception
     */
    public List<DbTopo> listerTopos(Integer idUser) throws Exception {
        return jpaCtrl.findListeTopos(idUser );
    }
    /**
     *
     * @param idUser
     * @return
     * @throws Exception
     */
    public DbTopo ajouterTopo(Integer idUser) throws Exception {

        DbTopo dbTopo = new DbTopo();
        Date date = Date.valueOf("2019-08-23");

        dbTopo.setDateDeParution(date);
        EtatsResaTopo etatsResaTopo = EtatsResaTopo.W_FR;
        dbTopo.setEtatReservation(etatsResaTopo);
        dbTopo.setLieu("Ici_par_Pgm");
        dbTopo.setEstPublie(true);
        dbTopo.setNom("nom_par_pgm");
        dbTopo.setResume("resume_par_pgm");
        dbTopo = jpaCtrl.createTopo(idUser, dbTopo);

        return dbTopo;
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

    public DbUser ajouterGrimpeur(String nomGrimpeur,
                                  String emailGrimpeur,
                                  String mdpGrimpeur,
                                  UserProfile userProfile) throws Exception {
        DbUser dbUser = new DbUser();
        dbUser.setNom(nomGrimpeur);
        dbUser.setEmail(emailGrimpeur);
        dbUser.setMdp(mdpGrimpeur);
        dbUser.setProfil(userProfile);

        return jpaCtrl.createUser(dbUser);
    }

    public DbUser modifierProfilGrimpeur(Integer idUser, UserProfile userProfile) throws Exception {

        return jpaCtrl.updateUser(idUser, userProfile);
    }


}
