package fr.ocr.prj06.business;

import fr.ocr.prj06.entity.common.UserProfile;
import fr.ocr.prj06.entity.stub.DbCommentaire;
import fr.ocr.prj06.entity.stub.DbSpot;
import fr.ocr.prj06.entity.stub.DbTopo;
import fr.ocr.prj06.entity.stub.DbUser;

import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws Exception {

        try (FileWriter fileWriter = new FileWriter("logs/project065.log")) {

            fileWriter.write("Hello from : " +App.class.getSimpleName());
            fileWriter.write("\n");

            BusinessMgmt businessMgmt = new BusinessMgmt();


            try {
                businessMgmt.openDAO();

                DbUser dbUser = businessMgmt.ajouterGrimpeur("laurentPgm", "laurent@laurent.pgm", "mdpPgm", UserProfile.MEMBRE);

                DbSpot dbSpot = businessMgmt.ajouterSpot(dbUser.getIduser());

                businessMgmt.ajouterTopo(dbUser.getIduser());

                businessMgmt.ajouterCommentaire(dbSpot.getIdspot(), "Hello - insertion par pgm", true);

                DbCommentaire dbCommentaire = businessMgmt.ajouterCommentaire(dbSpot.getIdspot(), "Hello bis - insertion par pgm", true);

                businessMgmt.modifierProfilGrimpeur(dbUser.getIduser(), UserProfile.GRIMPEUR);

                dbCommentaire = businessMgmt.modiferCommentaire(dbCommentaire.getIdcommentaire(), "Moderation", true);

                ArrayList<DbCommentaire> lstCmt = (ArrayList<DbCommentaire>) businessMgmt.listerTousLesCommentaires(dbSpot.getIdspot());
                fileWriter.write("Liste Complete Commentaires du spot :" + Integer.valueOf(dbSpot.getIdspot()).toString());
                fileWriter.write("\n");
                for (DbCommentaire x : lstCmt) {
                    fileWriter.write("-->" + x.toString());
                    fileWriter.write("\n");
                }

                fileWriter.write("Suppression commentaire id = " + Integer.valueOf(dbCommentaire.getIdcommentaire()).toString());
                fileWriter.write("\n");
                businessMgmt.supprimerCommentaire(dbCommentaire.getIdcommentaire(),false);

                lstCmt = (ArrayList<DbCommentaire>) businessMgmt.listerCommentairesActifs(dbSpot.getIdspot());
                fileWriter.write("Liste  Commentaires actifs (non supprimés)");
                fileWriter.write("\n");
                for (DbCommentaire x : lstCmt) {
                    fileWriter.write("-->" + x.toString());
                    fileWriter.write("\n");
                }
                lstCmt = (ArrayList<DbCommentaire>) businessMgmt.listerCommentairesArchives(dbSpot.getIdspot());
                fileWriter.write("Liste  Commentaires archivés (supprimés)");
                fileWriter.write("\n");
                for (DbCommentaire x : lstCmt) {
                    fileWriter.write("-->" + x.toString());
                    fileWriter.write("\n");
                }

                businessMgmt.closeDao();
            } catch (Exception ex) {
                businessMgmt.closeDao();
                throw new Exception(ex);
            }
        }
    }

}
