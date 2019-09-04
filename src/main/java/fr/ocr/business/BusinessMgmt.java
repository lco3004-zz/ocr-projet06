package fr.ocr.business;


import fr.ocr.model.controllers.JpaCtrlSpot;
import fr.ocr.model.controllers.JpaEntityManagerFactory;

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

    public BusinessMgmt()  {
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
