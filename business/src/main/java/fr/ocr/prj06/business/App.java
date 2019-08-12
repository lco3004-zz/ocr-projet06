package fr.ocr.prj06.business;

import fr.ocr.prj06.business.businessCtrl.UserMgmt;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        (new UserMgmt()).getListUsers();
    }
}
