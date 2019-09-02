package fr.ocr.prj06.business;

import fr.ocr.prj06.controllers.JpaEntityManagerFactory;

/**
 *
 */
public class BusinessMgmt {


    private JpaEntityManagerFactory jpaEmf;

    /* ************************************************************************************************
     * OUVERTURE/FERMETURE
     * ***********************************************************************************************
     */

    /**
     * @throws Exception
     */
    public BusinessMgmt() throws Exception {

        jpaEmf = JpaEntityManagerFactory.getJpaEntityManagerFactory();
    }

    public void openDAO()  {
            jpaEmf.openDao();
    }

    public void closeDao() {

        jpaEmf.closeDao();
    }

}
