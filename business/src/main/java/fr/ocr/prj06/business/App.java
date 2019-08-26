package fr.ocr.prj06.business;

import fr.ocr.prj06.entity.common.UserProfile;
import fr.ocr.prj06.entity.stub.*;

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

                DbUser [] dbUsers = new DbUser[2];

                dbUsers[0] = businessMgmt.ajouterGrimpeur("laurentPgm", "laurent@laurent.pgm", "mdpPgm", UserProfile.GRIMPEUR);

                dbUsers[1] = businessMgmt.ajouterGrimpeur("CordierPgm", "cordiert@laurent.pgm", "mdpPgm", UserProfile.GRIMPEUR);

                fileWriter.write("Liste Complete Users  :" );
                fileWriter.write("\n");
                for (DbUser x : dbUsers) {
                    fileWriter.write("-->" + x.toString());
                    fileWriter.write("\n");
                }
                fileWriter.write("\n");

                dbUsers[1] = businessMgmt.modifierProfilGrimpeur(dbUsers[1].getIduser(), UserProfile.MEMBRE);

                fileWriter.write("Liste Complete Users  apres Update profile:" );
                fileWriter.write("\n");
                for (DbUser x : dbUsers) {
                    fileWriter.write("-->" + x.toString());
                    fileWriter.write("\n");
                }
                fileWriter.write("\n");
                fileWriter.write("\n");

                DbSpot dbSpot = businessMgmt.ajouterSpot(dbUsers[1].getIduser());
                fileWriter.write("Liste Complete Spot  :" + dbSpot.toString());
                fileWriter.write("\n");
                fileWriter.write("Liste Complete Secteur du Spot  :" + Integer.valueOf(dbSpot.getIdspot()));
                fileWriter.write("\n");
                fileWriter.write("\n");
                for (DbSecteur dbSecteur: dbSpot.getSecteursByIdspot()) {
                    fileWriter.write("Secteur  :" + dbSecteur.toString());
                    fileWriter.write("\n");
                    fileWriter.write("Liste Complete Voie du Secteur  :" + Integer.valueOf(dbSecteur.getIdsecteur()));
                    fileWriter.write("\n");
                    fileWriter.write("\n");
                    for (DbVoie dbVoie : dbSecteur.getVoiesByIdsecteur()) {
                        fileWriter.write("Voie  :" + dbVoie.toString());
                        fileWriter.write("\n");
                        fileWriter.write("Liste Complete Longeur de la Voie  :" + Integer.valueOf(dbVoie.getIdvoie()));
                        fileWriter.write("\n");
                        fileWriter.write("\n");
                        for (DbLongueur dbLongueur : dbVoie.getLongueursByIdvoie()) {
                            fileWriter.write("Longueur  :" + dbLongueur.toString());
                            fileWriter.write("\n");
                        }
                    }
                }
                fileWriter.write("\n");
                fileWriter.write("\n");


                businessMgmt.ajouterTopo(dbUsers[0].getIduser());

                businessMgmt.ajouterCommentaire(dbSpot.getIdspot(), "Hello - insertion par pgm", true);

                DbCommentaire dbCommentaire = businessMgmt.ajouterCommentaire(dbSpot.getIdspot(), "Hello bis - insertion par pgm", true);

                ArrayList<DbCommentaire> lstCmt = (ArrayList<DbCommentaire>) businessMgmt.listerTousLesCommentaires(dbSpot.getIdspot());
                fileWriter.write("Liste Complete Commentaires du spot :" + Integer.valueOf(dbSpot.getIdspot()).toString());
                fileWriter.write("\n");
                fileWriter.write("\n");
                for (DbCommentaire x : lstCmt) {
                    fileWriter.write("-->" + x.toString());
                    fileWriter.write("\n");
                }
                fileWriter.write("\n");
                fileWriter.write("\n");

                dbCommentaire = businessMgmt.modiferCommentaire(dbCommentaire.getIdcommentaire(), "Moderation", true);

                 lstCmt = (ArrayList<DbCommentaire>) businessMgmt.listerTousLesCommentaires(dbSpot.getIdspot());
                fileWriter.write("Liste Complete Commentaires du spot : " + Integer.valueOf(dbSpot.getIdspot()).toString() + " ,  APRES Moderation" );
                fileWriter.write("\n");
                fileWriter.write("\n");
                for (DbCommentaire x : lstCmt) {
                    fileWriter.write("-->" + x.toString());
                    fileWriter.write("\n");
                }
                fileWriter.write("\n");
                fileWriter.write("\n");

                fileWriter.write("Suppression commentaire id = " + Integer.valueOf(dbCommentaire.getIdcommentaire()).toString());
                fileWriter.write("\n");
                businessMgmt.supprimerCommentaire(dbCommentaire.getIdcommentaire(),false);

                lstCmt = (ArrayList<DbCommentaire>) businessMgmt.listerCommentairesActifs(dbSpot.getIdspot());
                fileWriter.write("Liste  Commentaires actifs (non supprimés)");
                fileWriter.write("\n");
                fileWriter.write("\n");
                for (DbCommentaire x : lstCmt) {
                    fileWriter.write("-->" + x.toString());
                    fileWriter.write("\n");
                }
                fileWriter.write("\n");
                fileWriter.write("\n");
                lstCmt = (ArrayList<DbCommentaire>) businessMgmt.listerCommentairesArchives(dbSpot.getIdspot());
                fileWriter.write("Liste  Commentaires archivés (supprimés)");
                fileWriter.write("\n");
                fileWriter.write("\n");
                for (DbCommentaire x : lstCmt) {
                    fileWriter.write("-->" + x.toString());
                    fileWriter.write("\n");
                }
                fileWriter.write("\n");
                fileWriter.write("\n");

                businessMgmt.closeDao();
            } catch (Exception ex) {
                businessMgmt.closeDao();
                throw new Exception(ex);
            }
        }
    }

}
