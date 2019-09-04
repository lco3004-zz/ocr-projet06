package fr.ocr.prj06.business;


import fr.ocr.prj06.entities.DbSpot;
import fr.ocr.prj06.filelogs.LogsProjet;


/**
 * Hello world!
 */
public class App {

    public  void lolo(String[] args) throws Exception {

            BusinessMgmt businessMgmt = new BusinessMgmt();
            LogsProjet logs = LogsProjet.getLogsInstance(App.class);

            logs.info_projet("hello");


            try {
                businessMgmt.openDAO();
                DbSpot dbSpot = businessMgmt.ajouterSpot(2);
                businessMgmt.closeDao();
            } catch (Exception ex) {
                businessMgmt.closeDao();
                throw new Exception(ex);
            }
    }
}
