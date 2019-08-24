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
                DbUser dbUser2 = businessMgmt.ajouterGrimpeur("cordierPgm", "cordier@cordier.pgm", "mdpPgm", UserProfile.MEMBRE);

                DbSpot dbSpot = businessMgmt.ajouterSpot(dbUser.getIduser());
                DbSpot dbSpot1 = businessMgmt.ajouterSpot(dbUser.getIduser());
                DbSpot dbSpot2= businessMgmt.ajouterSpot(dbUser2.getIduser());
                DbSpot dbSpot3 = businessMgmt.ajouterSpot(dbUser.getIduser());

                DbTopo dbTopo = businessMgmt.ajouterTopo(dbUser.getIduser());
                dbTopo = businessMgmt.ajouterTopo(dbUser2.getIduser());
                dbTopo = businessMgmt.ajouterTopo(dbUser.getIduser());

                DbCommentaire dbCommentaire = businessMgmt.ajouterCommentaire(dbSpot.getIdspot(), "Hello - insertion par pgm", true);
                dbCommentaire = businessMgmt.ajouterCommentaire(dbSpot1.getIdspot(), "Hello - insertion par pgm", true);
                dbCommentaire = businessMgmt.ajouterCommentaire(dbSpot2.getIdspot(), "Hello - insertion par pgm", true);
                dbCommentaire = businessMgmt.ajouterCommentaire(dbSpot.getIdspot(), "Hello - insertion par pgm", true);

                dbUser = businessMgmt.modifierProfilGrimpeur(dbUser.getIduser(), UserProfile.GRIMPEUR);

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
                fileWriter.write("Liste  Commentaires actifs (non supprim�s)");
                fileWriter.write("\n");
                for (DbCommentaire x : lstCmt) {
                    fileWriter.write("-->" + x.toString());
                    fileWriter.write("\n");
                }
                lstCmt = (ArrayList<DbCommentaire>) businessMgmt.listerCommentairesArchives(dbSpot.getIdspot());
                fileWriter.write("Liste  Commentaires archiv�s (supprim�s)");
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
