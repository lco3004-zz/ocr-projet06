package fr.ocr.prj06;

import fr.ocr.prj06.logs.LogsProjet;
import fr.ocr.prj06.tools.Jpa_HibernateUtil;
import fr.ocr.prj06.tstdao.dbLongueurEntity;
import fr.ocr.prj06.tstdao.dbUtilisateurEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.Level;

import javax.persistence.EntityManagerFactory;

import static fr.ocr.prj06.logs.LogsProjet.geLogsInstance;
import static fr.ocr.prj06.messages.Messages.InfosMessages.LANCEMENT_APPLICATION;
import static fr.ocr.prj06.tools.Jpa_HibernateUtil.getEmf;
import static fr.ocr.prj06.tools.Jpa_HibernateUtil.getInstanceJpa_HibernateUtil;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(final String[] args) throws Exception {
        //
        try (LogsProjet logs = geLogsInstance(App.class))  {
            Properties properties = new Properties();
            try (InputStream inputStream = App.class.getResourceAsStream("/info.properties")) {
                properties.load(inputStream);

                logs.maTrace(org.apache.logging.log4j.Level.INFO,String.format("%s Version= %s",LANCEMENT_APPLICATION.getMessageInfos(),properties.getProperty("version")));

                try (Jpa_HibernateUtil jpa_hibernateUtil = getInstanceJpa_HibernateUtil()) {
                    getEmf();
                }
            }
        }
        catch (Exception ecp) {
            System.out.println("Exception "+ecp.getLocalizedMessage());
            throw new Exception(ecp);
        }
    }
}
