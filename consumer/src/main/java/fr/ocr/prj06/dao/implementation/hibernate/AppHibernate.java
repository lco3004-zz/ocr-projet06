package fr.ocr.prj06.dao.implementation.hibernate;

import fr.ocr.prj06.dao.implementation.hibernate.stubs.dbUtilisateurEntity;
import fr.ocr.prj06.logs.LogsProjet;
import org.apache.logging.log4j.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.io.InputStream;
import java.util.Properties;

import static fr.ocr.prj06.dao.implementation.hibernate.tools.HibernateUtil.getSession;
import static fr.ocr.prj06.messages.Messages.InfosMessages.LANCEMENT_APPLICATION;
import static fr.ocr.prj06.logs.LogsProjet.geLogsInstance;

public class AppHibernate {

    public static void main(final String[] args) throws Exception {
        //
        try (LogsProjet logs = geLogsInstance(AppHibernate.class))  {
            Properties properties = new Properties();
            try (InputStream inputStream = AppHibernate.class.getResourceAsStream("/info.properties")) {
                properties.load(inputStream);
                logs.maTrace(Level.INFO,String.format("%s Version= %s",LANCEMENT_APPLICATION.getMessageInfos(),properties.getProperty("version")));
                Session session = getSession();
                try {
                    session.beginTransaction();
                    dbUtilisateurEntity x =null;
                    x  = session.get(dbUtilisateurEntity.class,1);

                    System.out.println("-> email :" +x.getEmail()+" nom :"+x.getNom());
                    x.setNom(x.getNom()+" moi !!");
                    session.getTransaction().commit();

                }catch (HibernateException ex1) {
                    logs.maTrace(Level.ERROR,"Exception Hibernate :"+ex1.getLocalizedMessage());
                    if (session != null) {
                        try {
                            session.getTransaction().rollback();
                        }
                        catch (HibernateException ex2) {
                            logs.maTrace(Level.ERROR,"Exception Hibernate :"+ex2.getLocalizedMessage());
                        }
                    }
                }
                finally {
                    session.close();
                }
            }
        }
        catch (Exception ecp) {
            System.out.println("Exception "+ecp.getLocalizedMessage());
            throw new Exception(ecp);
        }
    }
}