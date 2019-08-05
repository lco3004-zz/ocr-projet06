package fr.ocr.prj06;

import fr.ocr.prj06.logs.LogsProjet;
import fr.ocr.prj06.tools.Jpa_HibernateUtil;

import java.io.InputStream;
import java.util.Properties;

import static fr.ocr.prj06.logs.LogsProjet.geLogsInstance;
import static fr.ocr.prj06.messages.Messages.InfosMessages.LANCEMENT_APPLICATION;
import static fr.ocr.prj06.tools.Jpa_HibernateUtil.*;


/**
 * Hello world!
 *
 */
public class AppJpa
{
    public static void main(final String[] args) throws Exception {
        //
        try (LogsProjet logs = geLogsInstance(AppJpa.class))  {
            Properties properties = new Properties();
            try (InputStream inputStream = AppJpa.class.getResourceAsStream("/info.properties")) {
                properties.load(inputStream);

                logs.maTrace(org.apache.logging.log4j.Level.INFO,String.format("%s Version= %s",LANCEMENT_APPLICATION.getMessageInfos(),properties.getProperty("version")));

                try (Jpa_HibernateUtil jpa_hibernateUtil = getInstanceJpa_HibernateUtil()) {
                    getEt().begin();

                    getEt().commit();
                }
            }
        }
        catch (Exception ecp) {
            System.out.println("Exception "+ecp.getLocalizedMessage());
            throw new Exception(ecp);
        }
    }
}
