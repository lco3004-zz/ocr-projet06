package fr.ocr.prj06.tools;

import fr.ocr.prj06.logs.LogsProjet;
import org.apache.logging.log4j.Level;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

public class Jpa_HibernateUtil implements AutoCloseable{

    private static  EntityManagerFactory emf =null;

    private static Jpa_HibernateUtil jpa_hibernateUtil=null;

    public  Jpa_HibernateUtil() {
        getEmf();
    }

    public static EntityManagerFactory getEmf() throws ExceptionInInitializerError {
        try {
            LogsProjet.geLogsInstance(Jpa_HibernateUtil.class).maTrace(Level.DEBUG,"Creation EntityManagerFactory ");
            if (emf == null) {
                emf= Persistence.createEntityManagerFactory("projet_06");
            }
            LogsProjet.geLogsInstance(Jpa_HibernateUtil.class).maTrace(Level.DEBUG,"EntityManagerFactory a été créée ");
        } catch (Throwable ex) {
            LogsProjet.geLogsInstance(Jpa_HibernateUtil.class).maTrace(Level.FATAL,"Impossible ouvrir session Hibernate :"+ex.getLocalizedMessage());
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
        } catch (Throwable ex) {
            LogsProjet.geLogsInstance(Jpa_HibernateUtil.class).maTrace(Level.FATAL,"Impossible fermer EntityManagerFactory :"+ex.getLocalizedMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }
}
