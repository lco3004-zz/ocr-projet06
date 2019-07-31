package fr.ocr.prj06.hibernate.tools;

import fr.ocr.prj06.hibernate.logs.LogsProjet;
import org.apache.logging.log4j.Level;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            LogsProjet.geLogsInstance(HibernateUtil.class).maTrace(Level.DEBUG,"Creation sessionFactory ");
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
            LogsProjet.geLogsInstance(HibernateUtil.class).maTrace(Level.DEBUG,"sessionFactory a été créée ");
        } catch (Throwable ex) {
            LogsProjet.geLogsInstance(HibernateUtil.class).maTrace(Level.FATAL,"Impossible ouvrir session Hibernate :"+ex.getLocalizedMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        LogsProjet.geLogsInstance(HibernateUtil.class).maTrace(Level.DEBUG,"Ouverture Session  ");
        return ourSessionFactory.openSession();
    }

}
