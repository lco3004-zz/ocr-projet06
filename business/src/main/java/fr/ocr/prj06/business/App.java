package fr.ocr.prj06.business;

import fr.ocr.prj06.entity.common.UserProfile;
import fr.ocr.prj06.entity.stub.DbCommentaire;
import fr.ocr.prj06.entity.stub.DbSpot;
import fr.ocr.prj06.entity.stub.DbTopo;
import fr.ocr.prj06.entity.stub.DbUser;

import java.util.ArrayList;



/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws Exception {


            BusinessMgmt businessMgmt = new BusinessMgmt();


            try {
                businessMgmt.openDAO();

                DbUser dbUser = businessMgmt.ajouterGrimpeur("laurentPgm", "laurent@laurent.pgm", "mdpPgm", UserProfile.MEMBRE);

                DbSpot dbSpot = businessMgmt.ajouterSpot(dbUser.getIduser());

                DbTopo dbTopo = businessMgmt.ajouterTopo(dbUser.getIduser());

                DbCommentaire dbCommentaire = businessMgmt.ajouterCommentaire(dbSpot.getIdspot(), "Hello - insertion par pgm", true);

                dbUser = businessMgmt.modifierProfilGrimpeur(dbUser.getIduser(), UserProfile.MEMBRE);

                dbCommentaire = businessMgmt.modiferCommentaire(dbCommentaire.getIdcommentaire(), "Moderation", true);

                ArrayList<DbCommentaire> lstCmt = (ArrayList<DbCommentaire>) businessMgmt.listerCommentairesActifs(dbSpot.getIdspot());
                for (DbCommentaire x : lstCmt) {

                }

                businessMgmt.supprimerCommentaire(dbCommentaire.getIdcommentaire(),false);
                int idComment = dbCommentaire.getIdcommentaire();
                lstCmt = (ArrayList<DbCommentaire>) businessMgmt.listerCommentairesActifs(dbSpot.getIdspot());
                for (DbCommentaire x : lstCmt) {
                }

                businessMgmt.closeDao();
            } catch (Exception ex) {
                businessMgmt.closeDao();
                throw new Exception(ex);
            }
        }

}
