package fr.ocr.business;

import fr.ocr.prj06.controllers.JpaCtrlSpot;
import fr.ocr.prj06.controllers.JpaEntityManagerFactory;


/**
 *
 */
public class BusinessMgmt {
    private JpaCtrlSpot jpaCtrl;
    private JpaEntityManagerFactory jpaEmf;

    /* ************************************************************************************************
     * OUVERTURE/FERMETURE
     * ***********************************************************************************************
     */

    public BusinessMgmt() throws Exception {
        jpaCtrl = JpaCtrlSpot.JPA_CTRL_SPOT;

        jpaEmf = JpaEntityManagerFactory.getJpaEntityManagerFactory();
    }

    public void openDAO()  {

        jpaEmf.openDao();
    }

    public void closeDao() {

        jpaEmf.closeDao();
    }

}
