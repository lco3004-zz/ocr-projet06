package fr.ocr.prj06.business;

import fr.ocr.prj06.business.businessCtrl.CommentaireMgmt;
import fr.ocr.prj06.business.businessCtrl.PersistenceMgmt;
import fr.ocr.prj06.business.businessCtrl.TopoMgmt;
import fr.ocr.prj06.business.businessCtrl.UserMgmt;
import fr.ocr.prj06.model.Commentaire;
import fr.ocr.prj06.model.Spot;
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
            PersistenceMgmt persistenceMgmt = null;
            try {
                persistenceMgmt = new PersistenceMgmt();
                persistenceMgmt.openDAO();
                {
                    logs.maTrace(Level.DEBUG, "Appel Controlleur UsrMgmt");
                    (new UserMgmt()).getListUsers();
                    logs.maTrace(Level.DEBUG, "Appel Controlleur TopoMgmt");
                    (new TopoMgmt()).getListTopos();
                    logs.maTrace(Level.DEBUG, "Appel Controlleur CommentaireMgmt");
                    //idSpot == 1 - il existe
                    Commentaire commentaire = new Commentaire(1);
                    commentaire.setEstVisible(false);
                    commentaire.setIdSpot(1);
                    commentaire.setTexte("Hello - insertion par pgm");
                    CommentaireMgmt cmtMgmt = new CommentaireMgmt();
                    cmtMgmt.ajouterCommentaire(commentaire);
                    logs.maTrace(Level.DEBUG, "Commentaire après insert : " + commentaire.toString());
                    commentaire.setTexte("Olleh + noitresni rap mgp ");
                    cmtMgmt.modiferCommentaire(commentaire);
                    logs.maTrace(Level.DEBUG, "Commentaire après update Texte : " + commentaire.toString());
                    commentaire.setEstVisible(true);
                    cmtMgmt.modiferCommentaire(commentaire);
                    logs.maTrace(Level.DEBUG, "Commentaire après update isVisble : " + commentaire.toString());
                    Spot spot = new Spot();
                    spot.setIdspot(1);
                    ArrayList<Commentaire> lstCmt = cmtMgmt.listerCommentaires(spot);
                    logs.maTrace(Level.DEBUG, "Affiche Liste des Commentaires: ");
                    for (Commentaire x : lstCmt) {
                        logs.maTrace(Level.DEBUG, "->  : " + x.toString());
                    }
                    cmtMgmt.supprimerCommentaire(commentaire);
                    logs.maTrace(Level.DEBUG, "Liste  après suppression   ");
                    lstCmt = cmtMgmt.listerCommentaires(spot);
                    for (Commentaire x : lstCmt) {
                        logs.maTrace(Level.DEBUG, "->  : " + x.toString());
                    }
                }
                persistenceMgmt.closeDao();
                logs.maTrace(Level.DEBUG, "****Business ****** Fin Main ");
            } catch (Exception ex) {
                if (persistenceMgmt != null)
                    persistenceMgmt.closeDao();
                logs.maTrace(Level.ERROR, "Erreur dans Main : " + ex.getLocalizedMessage());
                throw new Exception(ex);
            }
        }
    }
}
