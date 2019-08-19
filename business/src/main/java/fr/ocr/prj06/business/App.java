package fr.ocr.prj06.business;

import fr.ocr.prj06.entity.common.UserProfile;
import fr.ocr.prj06.entity.stub.DbCommentaire;
import fr.ocr.prj06.entity.stub.DbSpot;
import fr.ocr.prj06.entity.stub.DbTopo;
import fr.ocr.prj06.entity.stub.DbUser;
import fr.ocr.prj06.utility.logs.LogsProjet;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;

import static fr.ocr.prj06.utility.logs.LogsProjet.getLogsInstance;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws Exception {

        try (LogsProjet logs = getLogsInstance()) {
            logs.maTrace(Level.DEBUG, "****Business ******  Debut Main ");
            BusinessMgmt businessMgmt = new BusinessMgmt();


            try {
                businessMgmt.openDAO();

                DbUser dbUser = businessMgmt.ajouterGrimpeur("laurentPgm", "laurent@laurent.pgm", "mdpPgm", UserProfile.MEMBRE);
                logs.maTrace(Level.DEBUG, "Creation Grimpeur  : " + dbUser.toString());

                DbSpot dbSpot = businessMgmt.ajouterSpot(dbUser.getIduser());
                logs.maTrace(Level.DEBUG, "Creation Spot  : " + dbSpot.toString());

                DbTopo dbTopo = businessMgmt.ajouterTopo(dbUser.getIduser());
                logs.maTrace(Level.DEBUG, "Creation Topo  : " + dbTopo.toString());

                DbCommentaire dbCommentaire = businessMgmt.ajouterCommentaire(dbSpot.getIdspot(), "Hello - insertion par pgm", true);
                logs.maTrace(Level.DEBUG, "Creation Commentaire  : " + dbCommentaire.toString());

                dbUser = businessMgmt.modifierProfilGrimpeur(dbUser.getIduser(), UserProfile.MEMBRE);
                logs.maTrace(Level.DEBUG, "Modificaion profil  Grimpeur  : " + dbUser.toString());

                dbCommentaire = businessMgmt.modiferCommentaire(dbCommentaire.getIdcommentaire(), "Moderation", true);
                logs.maTrace(Level.DEBUG, "Moderation Commentaire  : " + dbCommentaire.toString());

                ArrayList<DbCommentaire> lstCmt = (ArrayList<DbCommentaire>) businessMgmt.listerCommentaires(1);
                logs.maTrace(Level.DEBUG, "Affiche Liste des Commentaires: ");
                for (DbCommentaire x : lstCmt) {
                    logs.maTrace(Level.DEBUG, "->  : " + x.toString());
                }

                businessMgmt.supprimerCommentaire(dbCommentaire.getIdcommentaire());
                int idComment = dbCommentaire.getIdcommentaire();
                logs.maTrace(Level.DEBUG, "Suppression Commentaire  : " + idComment);

                logs.maTrace(Level.DEBUG, "Liste  après suppression   ");
                lstCmt = (ArrayList<DbCommentaire>) businessMgmt.listerCommentaires(1);
                for (DbCommentaire x : lstCmt) {
                    logs.maTrace(Level.DEBUG, "->  : " + x.toString());
                }

                businessMgmt.closeDao();
                logs.maTrace(Level.DEBUG, "****Business ****** Fin Main ");
            } catch (Exception ex) {
                businessMgmt.closeDao();
                logs.maTrace(Level.ERROR, "Erreur dans Main : " + ex.getLocalizedMessage());
                throw new Exception(ex);
            }
        }
    }

    void tstGestCommentaire(LogsProjet logs, BusinessMgmt businessMgmt) throws Exception {
        //idSpot == 1 - il existe
        DbCommentaire dbCommentaire;

        dbCommentaire = businessMgmt.ajouterCommentaire(1, "Hello - insertion par pgm", true);
        logs.maTrace(Level.DEBUG, "Commentaire après insert : " + dbCommentaire.toString());


        dbCommentaire = businessMgmt.modiferCommentaire(dbCommentaire.getIdcommentaire(),
                "Olleh + noitresni rap mgp ",
                dbCommentaire.getEstVisible());
        logs.maTrace(Level.DEBUG, "Commentaire après update Texte : " + dbCommentaire.toString());

        dbCommentaire = businessMgmt.modiferCommentaire(dbCommentaire.getIdcommentaire(),
                dbCommentaire.getTexte(),
                true);
        logs.maTrace(Level.DEBUG, "Commentaire après update isVisble : " + dbCommentaire.toString());

        ArrayList<DbCommentaire> lstCmt = (ArrayList<DbCommentaire>) businessMgmt.listerCommentaires(1);
        logs.maTrace(Level.DEBUG, "Affiche Liste des Commentaires: ");
        for (DbCommentaire x : lstCmt) {
            logs.maTrace(Level.DEBUG, "->  : " + x.toString());
        }
        businessMgmt.supprimerCommentaire(dbCommentaire.getIdcommentaire());
        logs.maTrace(Level.DEBUG, "Liste  après suppression   ");

        lstCmt = (ArrayList<DbCommentaire>) businessMgmt.listerCommentaires(1);
        for (DbCommentaire x : lstCmt) {
            logs.maTrace(Level.DEBUG, "->  : " + x.toString());
        }
        dbCommentaire = businessMgmt.lireCommentaire(3);
        logs.maTrace(Level.DEBUG, "Lecture d'un commentaire id=3  :" + dbCommentaire.toString());

    }
}
