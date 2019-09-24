/*
 * **********************************************************
 * Projet 06
 * Contr�leur : m�thode en lien EntityManagerFactory
 *
 * Le nom de chaque m�thode suffit � comprendre sa fonction
 * ************************************************************
 */

package fr.ocr.business;

import fr.ocr.model.controllers.JpaEntityManagerFactory;

/**
 *
 */
public class BusinessMgmt {
    private JpaEntityManagerFactory jpaEmf;

    /* ************************************************************************************************
     * OUVERTURE/FERMETURE
     * ***********************************************************************************************
     */

    public BusinessMgmt()  {

        jpaEmf = JpaEntityManagerFactory.getJpaEntityManagerFactory();
    }

    public void openDAO()  {

        jpaEmf.openDao();
    }

    public void closeDao() {

        jpaEmf.closeDao();
    }

}
