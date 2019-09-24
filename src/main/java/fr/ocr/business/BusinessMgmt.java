/*
 * **********************************************************
 * Projet 06
 * Contrôleur : méthode en lien EntityManagerFactory
 *
 * Le nom de chaque méthode suffit à comprendre sa fonction
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
