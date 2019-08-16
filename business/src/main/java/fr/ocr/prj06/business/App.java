package fr.ocr.prj06.business;

import fr.ocr.prj06.entity.stub.DbCommentaire;
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

                businessMgmt.ajouterSpot(1);

                businessMgmt.ajouterTopo(2);

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
