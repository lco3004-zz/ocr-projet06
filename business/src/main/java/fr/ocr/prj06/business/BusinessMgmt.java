package fr.ocr.prj06.business;

import fr.ocr.prj06.controller.JpaCtrl;
import fr.ocr.prj06.entity.common.JpaEmfInterface;
import fr.ocr.prj06.entity.common.UserProfile;
import fr.ocr.prj06.entity.stub.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static fr.ocr.prj06.entity.common.JpaEmfUtility.getInstanceEMF;
import static fr.ocr.prj06.entity.common.SpotClassification.STANDARD;

/**
 *
 */
public class BusinessMgmt {

    private JpaCtrl jpaCtrl;
    private JpaEmfInterface jpa;

    /**
     * @throws Exception
     */
    public BusinessMgmt() throws Exception {
        jpaCtrl = new JpaCtrl();
        jpa = getInstanceEMF();
    }

    /**
     *
     * @throws Exception
     */
    public void openDAO() throws Exception {
        jpa.openDao();
    }

    /**
     *
     * @throws Exception
     */
    public void closeDao() throws Exception {
        jpa.closeDao();
    }

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
    public void supprimerCommentaire(Integer idCommentaire) throws Exception {
        jpaCtrl.deleteCommentaire(idCommentaire);
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
    public List listerCommentaires(Integer idSpot) throws Exception {
        return jpaCtrl.findListeCommentaires(idSpot);
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

    /**
     *
     * @param idUser
     * @return
     * @throws Exception
     */
    public DbTopo ajouterTopo(Integer idUser) throws Exception {

        DbTopo dbTopo = new DbTopo();
        Date date = new Date(Calendar.AUGUST);

        dbTopo.setDateDeParution(date);
        dbTopo.setEstDisponible(false);
        dbTopo.setLieu("Ici_par_Pgm");
        dbTopo.setEstPublie(true);
        dbTopo.setNom("nom_par_pgm");
        dbTopo.setResume("resume_par_pgm");
        dbTopo = jpaCtrl.createTopo(idUser, dbTopo);

        return dbTopo;
    }

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

        ArrayList<DbSecteur> dbSecteurs = new ArrayList<>();
        ArrayList<DbVoie> dbVoies = new ArrayList<>();
        ArrayList<DbLongueur> dbLongueurs = new ArrayList<>();
        ArrayList<DbCommentaire> dbCommentaires = new ArrayList<>();

        dbCommentaire.setEstVisible(true);
        dbCommentaire.setTexte("commentaire_par_pgm");
        dbCommentaires.add(dbCommentaire);

        dbLongueur.setNom("longueur_par_pgm");
        dbLongueur.setCotation("8a");
        dbLongueur.setNombreDeSpits(12);
        dbLongueurs.add(dbLongueur);

        dbVoie.setNom("voie_par_pgm");
        dbVoie.setLongueursByIdvoie(dbLongueurs);
        dbVoies.add(dbVoie);

        dbSecteur.setNom("secteur_par_pgm");
        dbSecteur.setVoiesByIdsecteur(dbVoies);
        dbSecteurs.add(dbSecteur);

        dbSpot.setClassification(STANDARD.name());
        dbSpot.setLocalisation("localisation_par_pgm");
        dbSpot.setNom("spot_par_pgm");
        dbSpot.setSecteursByIdspot(dbSecteurs);
        dbSpot.setCommentairesByIdspot(dbCommentaires);

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
