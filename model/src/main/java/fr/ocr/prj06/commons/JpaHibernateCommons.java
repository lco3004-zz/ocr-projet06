package fr.ocr.prj06.commons;

import fr.ocr.prj06.logs.LogsProjet;
import org.apache.logging.log4j.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class JpaHibernateCommons implements AutoCloseable {

    private static EntityManagerFactory emf = null;

    private static EntityManager em = null;

    private static EntityTransaction et = null;

    private static JpaHibernateCommons jpaHibernateCommons = null;

    private JpaHibernateCommons() {
        LogsProjet.geLogsInstance(JpaHibernateCommons.class).maTrace(Level.DEBUG, "Creation singleton = " + this.getClass().getSimpleName());
    }

    public static EntityTransaction getEt() {
        try {
            LogsProjet.geLogsInstance(JpaHibernateCommons.class).maTrace(Level.DEBUG, "Creation EntityTransaction ");
            getEm();
            if (et == null) {
                et = em.getTransaction();
            }
            LogsProjet.geLogsInstance(JpaHibernateCommons.class).maTrace(Level.DEBUG, "EntityTransaction a été créée ");
        } catch (Throwable ex) {
            LogsProjet.geLogsInstance(JpaHibernateCommons.class).maTrace(Level.FATAL, "Impossible creé EntityTransaction :" + ex.getLocalizedMessage());
            throw new ExceptionInInitializerError(ex);
        }
        return et;
    }


    private static EntityManager getEm() {
        try {
            LogsProjet.geLogsInstance(JpaHibernateCommons.class).maTrace(Level.DEBUG, "Creation EntityManager ");
            getEmf();
            if (em == null) {
                em = emf.createEntityManager();
            }
            LogsProjet.geLogsInstance(JpaHibernateCommons.class).maTrace(Level.DEBUG, "EntityManager a été créée ");
        } catch (Throwable ex) {
            LogsProjet.geLogsInstance(JpaHibernateCommons.class).maTrace(Level.FATAL, "Impossible creé EntityManager :" + ex.getLocalizedMessage());
            throw new ExceptionInInitializerError(ex);
        }
        return em;
    }

    private static EntityManagerFactory getEmf() throws ExceptionInInitializerError {
        try {
            LogsProjet.geLogsInstance(JpaHibernateCommons.class).maTrace(Level.DEBUG, "Creation EntityManagerFactory ");
            if (emf == null) {
                emf = Persistence.createEntityManagerFactory("projet_06");
            }
            LogsProjet.geLogsInstance(JpaHibernateCommons.class).maTrace(Level.DEBUG, "EntityManagerFactory a été créée ");
        } catch (Throwable ex) {
            LogsProjet.geLogsInstance(JpaHibernateCommons.class).maTrace(Level.FATAL, "Impossible creé EntityManagerFactory :" + ex.getLocalizedMessage());
            throw new ExceptionInInitializerError(ex);
        }
        return emf;
    }

    public static JpaHibernateCommons getInstance() {
        if (jpaHibernateCommons == null)
            jpaHibernateCommons = new JpaHibernateCommons();
        return jpaHibernateCommons;

    }

    @Override
    public void close() throws ExceptionInInitializerError {
        try {
            if (emf != null) {
                emf.close();
                emf = null;
                LogsProjet.geLogsInstance(JpaHibernateCommons.class).maTrace(Level.DEBUG, "EntityManagerFactory a été fermée ");
            }
            if (em != null) {
                em.close();
                em = null;
                LogsProjet.geLogsInstance(JpaHibernateCommons.class).maTrace(Level.DEBUG, "EntityManager a été fermée ");
            }
        } catch (Throwable ex) {
            LogsProjet.geLogsInstance(JpaHibernateCommons.class).maTrace(Level.FATAL, "Impossible fermer EntityManager et/ou E.M.Factory :" + ex.getLocalizedMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }
}