package fr.ocr.prj06.business;

import fr.ocr.prj06.business.businessCtrl.UserMgmt;
import fr.ocr.prj06.utility.logs.LogsProjet;
import org.apache.logging.log4j.Level;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        LogsProjet.geLogsInstance(App.class).maTrace(Level.DEBUG, "****Business ******  Debut Main ");
        LogsProjet.geLogsInstance(App.class).maTrace(Level.DEBUG, "Appel Controlleur dbUserEntity");
        (new UserMgmt()).getListUsers();
        LogsProjet.geLogsInstance(App.class).maTrace(Level.DEBUG, "****Business ****** Fin Main ");
    }
}
