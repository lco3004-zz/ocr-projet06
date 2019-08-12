package fr.ocr.prj06.entity.common;

import fr.ocr.prj06.utility.logs.LogsProjet;
import org.apache.logging.log4j.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author Laurent
 * <p>
 * Singleton pour gestion centrale des 3 classes : Transaction, Manager, Factory de JPA (implémentation Hibernate)
 */
public class JpaUtility implements AutoCloseable {

    private  EntityManagerFactory emf = null;

    private  EntityManager em = null;

    private  EntityTransaction et = null;

    private static JpaUtility jpaUtility = null;

    private  String persistenceUnitName;

    /**
     * Constructeur qui attend en parametre, le nom l'unité de persistence
     * qui est nommée dans persistence.xml
     * @param p_persistenceUnitName  - String
     */
    private JpaUtility(String p_persistenceUnitName) {
        LogsProjet.geLogsInstance(JpaUtility.class).maTrace(Level.DEBUG, "Creation singleton = " + this.getClass().getSimpleName());
        this.persistenceUnitName = p_persistenceUnitName;
    }

    /**
     * renvoie instance du singleton - permet d'utiliser l'autocloseable try() {} ce qui ferme
     * l'entitymanager (mais pas le factory qui doit être fermé avec un closeEmFactory
     *
     * @param p_persistenceUnitName
     * @return jpaUtility  - le signleton
     */
    public static JpaUtility getInstance(String p_persistenceUnitName) {
        if (jpaUtility == null)
            jpaUtility = new JpaUtility(p_persistenceUnitName);
        return jpaUtility;

    }

    /**
     * Renvoie (ou crée et renvoi) une référence à un objet transaction
     * @return et - objet EntityTransaction
     */
    public synchronized EntityTransaction getEt() throws ExceptionInInitializerError {
        try {
            LogsProjet.geLogsInstance(JpaUtility.class).maTrace(Level.DEBUG, "Creation EntityTransaction ");
            getEm();
            if (et == null) {
                et = em.getTransaction();
            }
            LogsProjet.geLogsInstance(JpaUtility.class).maTrace(Level.DEBUG, "EntityTransaction a été créée ");
        } catch (Throwable ex) {
            LogsProjet.geLogsInstance(JpaUtility.class).maTrace(Level.FATAL, "Impossible creé EntityTransaction :" + ex.getLocalizedMessage());
            throw new ExceptionInInitializerError(ex);
        }
        return et;
    }

    /**
     * creation d'un EntityManagerFactory ou si existe déja retourne le membre "emf
     * @return em - objet EntityManager
     */
    public synchronized EntityManager getEm() throws ExceptionInInitializerError {
        try {
            LogsProjet.geLogsInstance(JpaUtility.class).maTrace(Level.DEBUG, "Creation EntityManager ");
            getEmf();
            if (em == null) {
                em = emf.createEntityManager();
            }
            LogsProjet.geLogsInstance(JpaUtility.class).maTrace(Level.DEBUG, "EntityManager a été créée ");
        } catch (Throwable ex) {
            LogsProjet.geLogsInstance(JpaUtility.class).maTrace(Level.FATAL, "Impossible creé EntityManager :" + ex.getLocalizedMessage());
            throw new ExceptionInInitializerError(ex);
        }
        return em;
    }

    /**
     * creation d'un EntityManagerFactory ou si existe déja retourne le membre "emf
     * @return emf - objet EntityManagerFactory
     * @throws ExceptionInInitializerError
     */
    public synchronized EntityManagerFactory getEmf() throws ExceptionInInitializerError {
        try {
            LogsProjet.geLogsInstance(JpaUtility.class).maTrace(Level.DEBUG, "Creation EntityManagerFactory ");
            if (emf == null) {
                emf = Persistence.createEntityManagerFactory(persistenceUnitName);
            }
            LogsProjet.geLogsInstance(JpaUtility.class).maTrace(Level.DEBUG, "EntityManagerFactory a été créée ");
        } catch (Throwable ex) {
            LogsProjet.geLogsInstance(JpaUtility.class).maTrace(Level.FATAL, "Impossible creé EntityManagerFactory :" + ex.getLocalizedMessage());
            throw new ExceptionInInitializerError(ex);
        }
        return emf;
    }

    /**
     * fermeture EntityMangerFactory (n'entre pas dans le closeable - doit être appelelé à la main)
     * si EntityManager non fermé, le ferme aussi
     */
    public synchronized void closeEmFactory() throws ExceptionInInitializerError {
        try {
            close();
            if (emf != null) {
                emf.close();
                emf = null;
            }
            LogsProjet.geLogsInstance(JpaUtility.class).maTrace(Level.DEBUG, "EntityManagerFactory a été fermée ");
        } catch (Throwable ex) {
            LogsProjet.geLogsInstance(JpaUtility.class).maTrace(Level.FATAL, "Impossible fermer  E.M.Factory :" + ex.getLocalizedMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * ne ferme que l'entity manager  - closeable sur un try() {}
     * @throws ExceptionInInitializerError
     */
    @Override
    public synchronized void close() throws ExceptionInInitializerError {
        try {
            if (em != null) {
                em.close();
                em = null;
            }
            LogsProjet.geLogsInstance(JpaUtility.class).maTrace(Level.DEBUG, "EntityManager a été fermée ");
        } catch (Throwable ex) {
            LogsProjet.geLogsInstance(JpaUtility.class).maTrace(Level.FATAL, "Impossible fermer EntityManager et/ou E.M.Factory :" + ex.getLocalizedMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }
}