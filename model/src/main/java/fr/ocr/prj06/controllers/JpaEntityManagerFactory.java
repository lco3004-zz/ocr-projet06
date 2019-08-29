package fr.ocr.prj06.controllers;


import fr.ocr.prj06.interfaces.JpaInterface_EntityManagerFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.InputStream;
import java.util.Properties;

import static fr.ocr.prj06.entity.converter.Messages.ConstantesPgm.UNITE_DE_PERSISTANCE;


/**
 * @author Laurent
 * <p>
 * Singleton pour gestion centrale des 3 classes : Transaction, Manager, Factory de JPA (implémentation Hibernate)
 */
public class JpaEntityManagerFactory implements JpaInterface_EntityManagerFactory {

    private EntityManagerFactory emf = null;

    private static JpaEntityManagerFactory jpaEMFUtility = null;

    private  String persistenceUnitName;


    /**
     * Constructeur qui attend en parametre, le nom l'unité de persistence
     * qui est nommée dans persistence.xml
     */
    private JpaEntityManagerFactory()  {

        Properties properties = new Properties();
        try {
            InputStream inputStream = JpaEntityManagerFactory.class.getResourceAsStream("/info.properties");
            properties.load(inputStream);
            this.persistenceUnitName = properties.getProperty(UNITE_DE_PERSISTANCE.getValeurConstante());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * renvoie instance du singleton - permet d'utiliser l'autocloseable try() {} ce qui ferme
     * l'entitymanager (mais pas le factory qui doit être fermé avec un closeEmFactory
     *
     * @return jpaEMFUtility  - le signleton
     */
    public static JpaEntityManagerFactory getJpaEMFUtility()  {
        if (jpaEMFUtility == null)
            jpaEMFUtility = new JpaEntityManagerFactory();
        return jpaEMFUtility;
    }

    /**
     * creation d'un EntityManagerFactory ou si existe déja retourne le membre "emf
     * @return emf - objet EntityManagerFactory
     * @throws Exception
     */
    public synchronized EntityManagerFactory getEmf()  {
        try {
            if (emf == null) {
                emf = Persistence.createEntityManagerFactory(persistenceUnitName);
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return emf;
    }
    /**
     * fermeture EntityMangerFactory
     *
     */
    public synchronized void closeEmf()  {
        try {
            if (emf != null) {
                emf.close();
                emf = null;
            }
        } catch (Throwable ex) {
           ex.printStackTrace();
        }
    }

    @Override
    public void openDao()  {
        getEmf();
    }

    @Override
    public void closeDao()  {
        closeEmf();
    }
}
