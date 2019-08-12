package fr.ocr.prj06.entity.common;

import fr.ocr.prj06.utility.logs.LogsProjet;
import org.apache.logging.log4j.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class JpaHibernateUtility implements AutoCloseable {

    private  EntityManagerFactory emf = null;

    private  EntityManager em = null;

    private  EntityTransaction et = null;

    private  static JpaHibernateUtility jpaHibernateUtility = null;

    private  String persistenceUnitName;

    private JpaHibernateUtility(String p_persistenceUnitName) {
        LogsProjet.geLogsInstance(JpaHibernateUtility.class).maTrace(Level.DEBUG, "Creation singleton = " + this.getClass().getSimpleName());
        this.persistenceUnitName = p_persistenceUnitName;
    }

    public synchronized EntityTransaction getEt() {
        try {
            LogsProjet.geLogsInstance(JpaHibernateUtility.class).maTrace(Level.DEBUG, "Creation EntityTransaction ");
            getEm();
            if (et == null) {
                et = em.getTransaction();
            }
            LogsProjet.geLogsInstance(JpaHibernateUtility.class).maTrace(Level.DEBUG, "EntityTransaction a été créée ");
        } catch (Throwable ex) {
            LogsProjet.geLogsInstance(JpaHibernateUtility.class).maTrace(Level.FATAL, "Impossible creé EntityTransaction :" + ex.getLocalizedMessage());
            throw new ExceptionInInitializerError(ex);
        }
        return et;
    }


    public synchronized EntityManager getEm() {
        try {
            LogsProjet.geLogsInstance(JpaHibernateUtility.class).maTrace(Level.DEBUG, "Creation EntityManager ");
            getEmf();
            if (em == null) {
                em = emf.createEntityManager();
            }
            LogsProjet.geLogsInstance(JpaHibernateUtility.class).maTrace(Level.DEBUG, "EntityManager a été créée ");
        } catch (Throwable ex) {
            LogsProjet.geLogsInstance(JpaHibernateUtility.class).maTrace(Level.FATAL, "Impossible creé EntityManager :" + ex.getLocalizedMessage());
            throw new ExceptionInInitializerError(ex);
        }
        return em;
    }

    public synchronized EntityManagerFactory getEmf() throws ExceptionInInitializerError {
        try {
            LogsProjet.geLogsInstance(JpaHibernateUtility.class).maTrace(Level.DEBUG, "Creation EntityManagerFactory ");
            if (emf == null) {
                emf = Persistence.createEntityManagerFactory(persistenceUnitName);
            }
            LogsProjet.geLogsInstance(JpaHibernateUtility.class).maTrace(Level.DEBUG, "EntityManagerFactory a été créée ");
        } catch (Throwable ex) {
            LogsProjet.geLogsInstance(JpaHibernateUtility.class).maTrace(Level.FATAL, "Impossible creé EntityManagerFactory :" + ex.getLocalizedMessage());
            throw new ExceptionInInitializerError(ex);
        }
        return emf;
    }

    public static JpaHibernateUtility getInstance(String p_persistenceUnitName) {
        if (jpaHibernateUtility == null)
            jpaHibernateUtility = new JpaHibernateUtility(p_persistenceUnitName);
        return jpaHibernateUtility;

    }

    @Override
    public synchronized void close() throws ExceptionInInitializerError {
        try {
            if (emf != null) {
                emf.close();
                emf = null;
                LogsProjet.geLogsInstance(JpaHibernateUtility.class).maTrace(Level.DEBUG, "EntityManagerFactory a été fermée ");
            }
            if (em != null) {
                em.close();
                em = null;
                LogsProjet.geLogsInstance(JpaHibernateUtility.class).maTrace(Level.DEBUG, "EntityManager a été fermée ");
            }
        } catch (Throwable ex) {
            LogsProjet.geLogsInstance(JpaHibernateUtility.class).maTrace(Level.FATAL, "Impossible fermer EntityManager et/ou E.M.Factory :" + ex.getLocalizedMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }
}