package fr.ocr.prj06.business;

import fr.ocr.prj06.controllers.JpaCtrl;
import fr.ocr.prj06.controllers.JpaEntityManagerFactory;

/**
 *
 */
public class BusinessMgmt {

    private JpaCtrl jpaCtrl;
    private JpaEntityManagerFactory jpaEmf;

    /* ************************************************************************************************
     * OUVERTURE/FERMETURE
     * ***********************************************************************************************
     */

    /**
     * @throws Exception
     */
    public BusinessMgmt() throws Exception {
        jpaCtrl = new JpaCtrl();
        jpaEmf = JpaEntityManagerFactory.getJpaEntityManagerFactory();
    }

    public void openDAO()  {
            jpaEmf.openDao();
    }

    public void closeDao() {

        jpaEmf.closeDao();
    }

}
