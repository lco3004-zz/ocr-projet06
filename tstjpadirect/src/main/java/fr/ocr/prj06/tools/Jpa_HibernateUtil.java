package fr.ocr.prj06.tools;

import fr.ocr.prj06.logs.LogsProjet;
import org.apache.logging.log4j.Level;

import javax.persistence.*;

public class Jpa_HibernateUtil implements AutoCloseable{

    private static  EntityManagerFactory emf =null;

    private static EntityManager em=null;

    private static EntityTransaction et=null;

    private static Jpa_HibernateUtil jpa_hibernateUtil=null;

    public  Jpa_HibernateUtil() {
        LogsProjet.geLogsInstance(Jpa_HibernateUtil.class).maTrace(Level.DEBUG,"Creation singleton = " + this.getClass().getSimpleName());
    }

    public static EntityTransaction getEt() {
        try {
            LogsProjet.geLogsInstance(Jpa_HibernateUtil.class).maTrace(Level.DEBUG,"Creation EntityTransaction ");
            getEm();
            if (et == null) {
                et= em.getTransaction();
            }
            LogsProjet.geLogsInstance(Jpa_HibernateUtil.class).maTrace(Level.DEBUG,"EntityTransaction a été créée ");
        } catch (Throwable ex) {
            LogsProjet.geLogsInstance(Jpa_HibernateUtil.class).maTrace(Level.FATAL,"Impossible creé EntityTransaction :"+ex.getLocalizedMessage());
            throw new ExceptionInInitializerError(ex);
        }
        return et;
    }


    public static EntityManager getEm() {
        try {
            LogsProjet.geLogsInstance(Jpa_HibernateUtil.class).maTrace(Level.DEBUG,"Creation EntityManager ");
            getEmf();
            if (em == null) {
                em= emf.createEntityManager();
            }
            LogsProjet.geLogsInstance(Jpa_HibernateUtil.class).maTrace(Level.DEBUG,"EntityManager a été créée ");
        } catch (Throwable ex) {
            LogsProjet.geLogsInstance(Jpa_HibernateUtil.class).maTrace(Level.FATAL,"Impossible creé EntityManager :"+ex.getLocalizedMessage());
            throw new ExceptionInInitializerError(ex);
        }
        return em;
    }

    public static EntityManagerFactory getEmf() throws ExceptionInInitializerError {
        try {
            LogsProjet.geLogsInstance(Jpa_HibernateUtil.class).maTrace(Level.DEBUG,"Creation EntityManagerFactory ");
            if (emf == null) {
                emf= Persistence.createEntityManagerFactory("projet_06");
            }
            LogsProjet.geLogsInstance(Jpa_HibernateUtil.class).maTrace(Level.DEBUG,"EntityManagerFactory a été créée ");
        } catch (Throwable ex) {
            LogsProjet.geLogsInstance(Jpa_HibernateUtil.class).maTrace(Level.FATAL,"Impossible creé EntityManagerFactory :"+ex.getLocalizedMessage());
            throw new ExceptionInInitializerError(ex);
        }
        return emf;
    }
    public static Jpa_HibernateUtil  getInstanceJpa_HibernateUtil () {
        if (jpa_hibernateUtil == null)
            jpa_hibernateUtil = new Jpa_HibernateUtil();
        return jpa_hibernateUtil;

    }

    @Override
    public void close() throws Exception {
        try {
            if (emf != null) {
                emf.close();
                emf=null;
                LogsProjet.geLogsInstance(Jpa_HibernateUtil.class).maTrace(Level.DEBUG,"EntityManagerFactory a été fermée ");
            }
            if (em != null) {
                em.close();
                em=null;
                LogsProjet.geLogsInstance(Jpa_HibernateUtil.class).maTrace(Level.DEBUG,"EntityManager a été fermée ");
}
        } catch (Throwable ex) {
            LogsProjet.geLogsInstance(Jpa_HibernateUtil.class).maTrace(Level.FATAL,"Impossible fermer EntityManager et/ou E.M.Factory :"+ex.getLocalizedMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }
}
