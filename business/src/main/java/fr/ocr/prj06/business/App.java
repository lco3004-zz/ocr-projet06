package fr.ocr.prj06.business;

import fr.ocr.prj06.business.businessCtrl.PersistenceMgmt;
import fr.ocr.prj06.business.businessCtrl.TopoMgmt;
import fr.ocr.prj06.business.businessCtrl.UserMgmt;
import fr.ocr.prj06.utility.logs.LogsProjet;
import org.apache.logging.log4j.Level;

import static fr.ocr.prj06.utility.logs.LogsProjet.getLogsInstance;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {

        try (LogsProjet logs = getLogsInstance()) {
            logs.maTrace(Level.DEBUG, "****Business ******  Debut Main ");

            try {
                PersistenceMgmt persistenceMgmt = new PersistenceMgmt();
                persistenceMgmt.openDAO();
                {
                    logs.maTrace(Level.DEBUG, "Appel Controlleur UsrMgmt");
                    (new UserMgmt()).getListUsers();
                    logs.maTrace(Level.DEBUG, "Appel Controlleur TopoMgmt");
                    (new TopoMgmt()).getListTopos();
                }
                persistenceMgmt.closeDao();
                logs.maTrace(Level.DEBUG, "****Business ****** Fin Main ");
            } catch (Exception ex) {
                logs.maTrace(Level.ERROR, "Erreur dans Main" + ex.getLocalizedMessage());
                throw new Exception(ex);
            }
        }

    }
}
