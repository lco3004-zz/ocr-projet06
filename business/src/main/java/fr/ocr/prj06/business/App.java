package fr.ocr.prj06.business;


import fr.ocr.prj06.entities.DbSpot;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws Exception {


            BusinessMgmt businessMgmt = new BusinessMgmt();


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
