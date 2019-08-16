package fr.ocr.prj06.business;

import fr.ocr.prj06.controller.JpaCtrl;
import fr.ocr.prj06.entity.common.JpaEmfInterface;
import fr.ocr.prj06.entity.stub.*;

import java.util.ArrayList;
import java.util.List;

import static fr.ocr.prj06.entity.common.JpaEmfUtility.getInstanceEMF;
import static fr.ocr.prj06.entity.common.SpotClassification.STANDARD;

public class BusinessMgmt {

    private JpaCtrl jpaCtrl;
    private JpaEmfInterface jpa;

    public BusinessMgmt() throws Exception {
        jpaCtrl = new JpaCtrl();
        jpa = getInstanceEMF();
    }

    public void openDAO() throws Exception {
        jpa.openDao();
    }

    public void closeDao() throws Exception {
        jpa.closeDao();
    }

    public DbCommentaire ajouterCommentaire(Integer idSpot, String txtComment, Boolean isVisible) throws Exception {
        return jpaCtrl.createCommentaire(idSpot, txtComment, isVisible);
    }

    public void supprimerCommentaire(Integer idCommentaire) throws Exception {
        jpaCtrl.deleteCommentaire(idCommentaire);
    }

    public DbCommentaire lireCommentaire(Integer id) throws Exception {
        return jpaCtrl.readCommentaire(id);
    }

    public List listerCommentaires(Integer idSpot) throws Exception {
        return jpaCtrl.findListeCommentaires(idSpot);
    }

    public DbCommentaire modiferCommentaire(Integer idCommentaire, String txtComment, Boolean isVisible) throws Exception {
        return jpaCtrl.updateCommentaire(idCommentaire, txtComment, isVisible);
    }

    public DbSpot ajouterSpot(Integer idUser) throws Exception {
        DbSpot dbSpot = new DbSpot();
        DbSecteur dbSecteur = new DbSecteur();
        DbVoie dbVoie = new DbVoie();
        DbLongueur dbLongueur = new DbLongueur();

        ArrayList<DbSecteur> dbSecteurs = new ArrayList<>();
        ArrayList<DbVoie> dbVoies = new ArrayList<>();

        dbLongueur.setNom("longueur_par_pgm");
        dbLongueur.setCotation("8a");
        dbLongueur.setNombreDeSpits(12);
        ArrayList<DbLongueur> dbLongueurs = new ArrayList<>();
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

        return jpaCtrl.createSpot(idUser, dbSpot);
    }
}
